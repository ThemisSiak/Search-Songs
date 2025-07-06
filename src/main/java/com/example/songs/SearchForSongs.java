package com.example.songs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SearchForSongs
 */
@WebServlet("/SearchForSongs")
public class SearchForSongs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SearchForSongs() {
		super();
	}
       
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int pageNumber = 1;
        String query = request.getParameter("query");
        String field = request.getParameter("field");
        
        //Retrieve the page number and results per page from the request parameters
        String pageinput =request.getParameter("page");
        if(pageinput!=null && !pageinput.isEmpty()) {
        	try {
        		pageNumber = Integer.parseInt(pageinput);
        	}catch(NumberFormatException e) {
        		e.printStackTrace();
        	}
        }
        //System.out.println("Query: " + query);
        if (query == null || query.isEmpty()) {
            request.setAttribute("error", "Please enter a search query.");
            request.getRequestDispatcher("/search.jsp").forward(request, response);
            return;
        }

        List<SongData> results = new ArrayList<>();
        try {
        	SongSearch searcher = new SongSearch("indexedFiles");
            results = searcher.search(query, field, pageNumber);
            //System.out.print(results);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while performing the search.");
        }

        request.setAttribute("query", query);
        request.setAttribute("field", field);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("results", results);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
