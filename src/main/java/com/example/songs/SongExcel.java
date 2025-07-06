package com.example.songs;

import java.io.IOException;

import org.apache.lucene.document.Document;

import java.io.BufferedReader;
import java.io.FileReader;

public class SongExcel{
	
	public SongExcel() {}
	
	public void readFile() throws IOException{
		//open file
		BufferedReader read = new BufferedReader(new FileReader("inputFile\\spotify_millsongdata.csv"));
		SongDocument doc = new SongDocument("indexedFiles");
		
		SongData newsong = new SongData();
		
		Document newdoc;
		String line = read.readLine();
		line =read.readLine();
		int i =1;	//number of document
		String temp = line;
		String temp2 = "\"" + "\"";
		
		while(line !=null) {
			String[] fields = line.split(",");
			String ar = fields[0];	//artist
			String t = fields[1];	//title
			String al = fields[2];	//album
			String l = fields[3];	//lyrics
			
			//check if the title has commas, so it seperates the line in more fields
			char firstChar = al.charAt(0);
			int j = 1;
			
			while(firstChar != '/') {
				t = t + "," + al;
				al = fields[2+j];
				l = fields[3+j];
				j ++;
				firstChar = al.charAt(0);
			}
			//System.out.print(fields.length);
			if(fields.length > 3+j) {
				l = l + fields[3+j];
			}
			
			StringBuilder sb = new StringBuilder(l);
			while(line != null && !line.endsWith("\"")) {
				line = read.readLine();
				temp = line;
				sb.append("\n");
				sb.append(line);
			}
			
			l = sb.toString();
		
			newsong.setArtist(stripHtmlTags(ar));
			newsong.setTitle(stripHtmlTags(t));
			newsong.setAlbum(stripHtmlTags(al));
			newsong.setLyrics(stripHtmlTags(l));
			
			newdoc = doc.indexSong(newsong);
			System.out.print(newdoc +"number"+ i);
			
			//check if in the end of the file we have "", read two more lines, because they are null
			//System.out.print(temp);
			if (temp.endsWith(temp2)) {
				line = read.readLine();
				line = read.readLine();
			}
			
			line =read.readLine();
			//System.out.print(line);
			i++;
		}
		doc.close();
		read.close();
		
	}
	
	private String stripHtmlTags(String input) {
	    if (input == null) return "";
	    return input.replaceAll("<[^>]*>", ""); // removes all <...> tags
	}

	
	public static void main(String[] args) throws IOException{
		SongExcel song = new SongExcel();
		song.readFile();
		System.out.print("The songs are indexed");
	}
}