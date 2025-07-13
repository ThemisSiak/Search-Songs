package com.example.songs;

import java.io.IOException;

import static org.junit.Assert.*;

import org.apache.lucene.document.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class SongDocumentTest {
	
	private SongDocument songDocument;
	private Path tempIndexPath;
	
	@Before
	public void setUp() throws IOException {
		tempIndexPath = Files.createTempDirectory("testIndex");
		songDocument = new SongDocument(tempIndexPath.toString());
	}
	
	@After
	public void tearDown() throws IOException {
		if (songDocument != null) {
			songDocument.close();
		}
		if (tempIndexPath != null) {
			Files.walk(tempIndexPath)
				 .map(Path::toFile)
				 .forEach(java.io.File::delete);
		}
	}
	
	@Test
	public void testIndexSingleSong() throws IOException {
		SongData song = new SongData("Test Artist", "Test Title", "Test Album", "Test Lyrics");
		Document doc = songDocument.indexSong(song);
		
		assertEquals("Test Artist", doc.get("artist"));
		assertEquals("Test Title", doc.get("title"));
		assertEquals("Test Album", doc.get("album"));
		assertEquals("Test Lyrics", doc.get("lyrics"));
	}
}
