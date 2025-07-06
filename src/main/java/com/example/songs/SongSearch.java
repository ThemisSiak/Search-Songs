package com.example.songs;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

public class SongSearch {
	
	private int maxResults = 50000;
	private final IndexSearcher searcher;
	private Directory indexDir;
	private IndexReader reader;
	
	public SongSearch(String indexPath) throws IOException{
		this.indexDir = FSDirectory.open(Paths.get(indexPath));
		this.reader = DirectoryReader.open(indexDir);
		searcher = new IndexSearcher(reader);
	}
	
	public List<SongData> search(String strfind, String field, int pagenum) throws Exception{
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser;
		if(!"all".equals(field)) {
			parser = new QueryParser(field, analyzer);
		}
		else {
			parser = new MultiFieldQueryParser(new String[] {"artist", "title", "album", "lyrics"}, analyzer);
		}
		Query q = parser.parse(strfind);
		
		//which page of results does user want to see
		TopDocs topd = searcher.search(q,  maxResults);
		int totalResultsCount = (int) topd.totalHits.value;
		
		int firstp = (pagenum-1) *10;	//10 results per page
		int lastp = Math.min(firstp + 10 -1, totalResultsCount-1);
		//TopDocs topd = searcher.search(q,  113);
		
		System.out.print("Total Result:" + topd.totalHits);
		System.out.print("\n");
		
		ScoreDoc[] hits = topd.scoreDocs;
		List<SongData> results = new ArrayList<>();
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<b style='color:purple'>", "</b>");

        for (int i = firstp; i <= lastp; i++) {
        	@SuppressWarnings("deprecation")
            Document document = searcher.doc(hits[i].doc);

            String artist = document.get("artist");
            String title = document.get("title");
            String album = document.get("album");
            String lyrics = document.get("lyrics");

            String hArtist = artist;
            String hTitle = title;
            String hAlbum = album;
            String hLyrics = lyrics;

            if ("all".equals(field)) {
                hArtist = highlightWithFieldQuery(analyzer, strfind, "artist", artist, formatter);
                hTitle = highlightWithFieldQuery(analyzer, strfind, "title", title, formatter);
                hAlbum = highlightWithFieldQuery(analyzer, strfind, "album", album, formatter);
                hLyrics = highlightWithFieldQuery(analyzer, strfind, "lyrics", lyrics, formatter);
            } else if ("artist".equals(field)) {
                hArtist = highlightWithFieldQuery(analyzer, strfind, "artist", artist, formatter);
            } else if ("title".equals(field)) {
                hTitle = highlightWithFieldQuery(analyzer, strfind, "title", title, formatter);
            } else if ("album".equals(field)) {
                hAlbum = highlightWithFieldQuery(analyzer, strfind, "album", album, formatter);
            } else if ("lyrics".equals(field)) {
                hLyrics = highlightWithFieldQuery(analyzer, strfind, "lyrics", lyrics, formatter);
            }

            SongData song = new SongData(hArtist, hTitle, hAlbum, hLyrics);

            String rawLyrics = document.get("lyrics");
            song.setPreviewLyrics(rawLyrics.length() > 200 ? rawLyrics.substring(0, 200) + "..." : rawLyrics);
            song.setTruncated(rawLyrics.length() > 200);

			results.add(song);
        }
	    
		return results;
	}
	
	private String highlightWithFieldQuery(Analyzer analyzer, String queryStr, String fieldName, String text, SimpleHTMLFormatter formatter) {
	    if (text == null || text.isEmpty()) return text;
	    try {
	        QueryParser fieldParser = new QueryParser(fieldName, analyzer);
	        Query fieldQuery = fieldParser.parse(queryStr);

	        QueryScorer scorer = new QueryScorer(fieldQuery, fieldName);
	        Highlighter highlighter = new Highlighter(formatter, scorer);
	        highlighter.setTextFragmenter(new org.apache.lucene.search.highlight.NullFragmenter()); // ✅ don't cut into snippets

	        TokenStream tokenStream = analyzer.tokenStream(fieldName, text);
	        String highlighted = highlighter.getBestFragment(tokenStream, text);

	        // Fallback: if Lucene still can't find a "fragment", just return the full text
	        return (highlighted != null && !highlighted.isEmpty()) ? highlighted : text;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return text;
	    }
	}
	
	public static void main(String[] args) throws Exception {
	    SongSearch searcher = new SongSearch("indexedFiles");
	    List<SongData> results = searcher.search("ABBA", "all", 4);
	    for (SongData song : results) {
	        System.out.println(song.getTitle() + "," + song.getArtist() + "," + song.getAlbum());
	    }
	}
}
