package com.example.songs;

import java.io.IOException;

import org.apache.lucene.document.Document;

import java.io.BufferedReader;
import java.io.FileReader;

public class SongExcel{
	
	public SongExcel() {}
	
	public void readFile() throws IOException{
		BufferedReader read = new BufferedReader(new FileReader("inputFile\\spotify_millsongdata.csv"));
		SongDocument doc = new SongDocument("indexedFiles");
		
		SongData newsong = new SongData();
		
		Document newdoc;
		String line = read.readLine();
		line =read.readLine();
		int numDocument =1;
		String temp = line;
		String temp2 = "\"" + "\"";
		
		while(line !=null) {
			String[] fields = line.split(",");
			String artist = fields[0];
			String title = fields[1];
			String album = fields[2];
			String lyrics = fields[3];
			
			//check if the title has commas, so it seperates the line in more fields
			char firstChar = album.charAt(0);
			int j = 1;
			
			while(firstChar != '/') {
				title = title + "," + album;
				album = fields[2+j];
				lyrics = fields[3+j];
				j ++;
				firstChar = album.charAt(0);
			}
			if(fields.length > 3+j) {
				lyrics = lyrics + fields[3+j];
			}
			
			StringBuilder sb = new StringBuilder(lyrics);
			while(line != null && !line.endsWith("\"")) {
				line = read.readLine();
				temp = line;
				sb.append("\n");
				sb.append(line);
			}
			
			lyrics = sb.toString();
		
			newsong.setArtist(stripHtmlTags(artist));
			newsong.setTitle(stripHtmlTags(title));
			newsong.setAlbum(stripHtmlTags(album));
			newsong.setLyrics(stripHtmlTags(lyrics));
			
			newdoc = doc.indexSong(newsong);
			System.out.print(newdoc +"number"+ numDocument + "\n");
			
			//check if in the end of the file we have "", read two more lines, because they are null
			if (temp.endsWith(temp2)) {
				line = read.readLine();
				line = read.readLine();
			}
			
			line =read.readLine();
			numDocument++;
		}
		doc.close();
		read.close();
		
	}
	
	private String stripHtmlTags(String input) {
	    if (input == null) return "";
	    return input.replaceAll("<[^>]*>", "");
	}

	
	public static void main(String[] args) throws IOException{
		SongExcel song = new SongExcel();
		song.readFile();
		System.out.print("The songs are indexed");
	}
}