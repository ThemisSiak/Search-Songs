package com.example.songs;

public class SongData {
	private String artist;
	private String title;
	private String album;
	private String lyrics;
	private String previewLyrics;
	private boolean truncated;
	
	//constructors
	public SongData() {
	}
	
	public SongData(String artist, String title, String album, String lyrics) {
		this.artist = artist;
		this.title = title;
		this.album = album;
		this.lyrics = lyrics;
	}
	
	//getters and setters
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getLyrics() {
		return lyrics;
	}
	
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getPreviewLyrics() {
	    return previewLyrics;
	}

	public void setPreviewLyrics(String previewLyrics) {
	    this.previewLyrics = previewLyrics;
	}

	public boolean isTruncated() {
	    return truncated;
	}

	public void setTruncated(boolean truncated) {
	    this.truncated = truncated;
	}

	
	@Override
	public String toString() {
		return "SongData [artist=" + artist + ", title=" + title + ", album=" + album + ", lyrics=" + lyrics;
	}

}
