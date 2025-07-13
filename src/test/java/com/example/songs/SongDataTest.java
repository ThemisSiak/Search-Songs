package com.example.songs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SongDataTest {
	
	@Test
	public void testConstructorAndGetters() {
		SongData song = new SongData("Artist", "Title", "Album", "Lyrics");
		
		assertEquals("Artist", song.getArtist());
		assertEquals("Title", song.getTitle());
		assertEquals("Album", song.getAlbum());
		assertEquals("Lyrics", song.getLyrics());
	}
	
	@Test
	public void testSetters() {
		SongData song = new SongData();
		song.setArtist("New Artist");
		song.setTitle("New Title");
		song.setAlbum("New Album");
		song.setLyrics("New Lyrics");
		
		assertEquals("New Artist", song.getArtist());
		assertEquals("New Title", song.getTitle());
		assertEquals("New Album", song.getAlbum());
        assertEquals("New Lyrics", song.getLyrics());
	}
}
