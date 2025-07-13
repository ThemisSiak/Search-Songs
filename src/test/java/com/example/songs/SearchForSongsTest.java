package com.example.songs;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class SearchForSongsTest {
	
	private SearchForSongs servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher dispatcher;
	private HttpSession session;
	
	@Before
	public void setUp() {
		servlet = new SearchForSongs();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		dispatcher = mock(RequestDispatcher.class);
		session = mock(HttpSession.class);
	}
	
	@Test
	public void testEmptyQueryShowsError() throws ServletException, IOException {
		when(request.getParameter("query")).thenReturn("");
		when(request.getRequestDispatcher("/search.jsp")).thenReturn(dispatcher);
		
		servlet.doPost(request, response);
		
		verify(request).setAttribute(eq("error"), anyString());
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	public void testValidQueryForwardsToResultJsp() throws Exception {
		when(request.getParameter("query")).thenReturn("love");
		when(request.getParameter("field")).thenReturn("lyrics");
		when(request.getParameter("page")).thenReturn("1");
		when(request.getRequestDispatcher("/result.jsp")).thenReturn(dispatcher);
		when(request.getSession()).thenReturn(session);
		
		SongSearch mockSearch = mock(SongSearch.class);
		when(mockSearch.search("love", "lyrics", 1)).thenReturn(new ArrayList<>());
		
		servlet.doPost(request, response);
		
		verify(dispatcher).forward(request, response);
	}
}
