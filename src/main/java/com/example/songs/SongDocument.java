package com.example.songs;

import java.nio.file.Paths;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SongDocument {
	private Directory index;
	private StandardAnalyzer analyzer;
	private IndexWriterConfig config;
	private IndexWriter writer;
	
	//constructors
	public SongDocument() {
	}
	
	public SongDocument(String indexPath) throws IOException{
		this.index = FSDirectory.open(Paths.get(indexPath));
		this.analyzer = new StandardAnalyzer();
		this.config = new IndexWriterConfig(analyzer);
		this.config.setOpenMode(OpenMode.CREATE);
		this.writer = new IndexWriter(index, config);
	}
	
	public Document indexSong(SongData song) throws IOException{
		Document document = new Document();
		
		//config.setOpenMode(OpenMode.CREATE);
		
		Field artist = new TextField("artist", song.getArtist(), Field.Store.YES);
		Field title = new TextField("title", song.getTitle(), Field.Store.YES);
		Field album = new TextField("album", song.getAlbum(), Field.Store.YES);
		Field lyrics = new TextField("lyrics", song.getLyrics(), Field.Store.YES);
		
		document.add(artist);
		document.add(title);
		document.add(album);
		document.add(lyrics);
		
		writer.addDocument(document);
		return document;
	}
	
	public void close() throws IOException{
		writer.close();
	}
}
