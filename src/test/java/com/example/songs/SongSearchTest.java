package com.example.songs;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SongSearchTest {
	
	private SongSearch songSearch;
	
	@Before
	public void setUp() throws Exception {
		songSearch = new SongSearch("indexedFiles");
	}
	
	@Test
	public void testSearchInTitle() throws Exception {
		List<SongData> results = songSearch.search("rain", "title", 1);
		assertTrue(results.size() > 0);
	}
	
	@Test
	public void testSearchInAlbum() throws Exception {
		List<SongData> results = songSearch.search("abba", "album", 1);
		assertTrue(results.size() > 0);
	}
	
	@Test
	public void testSearchInArtist() throws Exception {
		List<SongData> results = songSearch.search("ABBA", "artist", 1);
		assertTrue(results.size() > 0);
		assertTrue(results.stream().anyMatch(song -> song.getArtist().toLowerCase().contains("abba")));
	}

	@Test
	public void testSearchInLyrics() throws Exception {
		List<SongData> results = songSearch.search("love", "lyrics", 1);
		assertNotNull(results);
		assertTrue(results.size() > 0);
		assertEquals(10, results.size());
	}
	
	@Test
	public void testSearchAllFields() throws Exception {
		List<SongData> results = songSearch.search("ABBA", "all", 1);
		assertNotNull(results);
	}
}
