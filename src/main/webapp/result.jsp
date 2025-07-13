<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Results</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styling.css">
</head>
<body>
	
	<h1>Song Search Results</h1>
	<h3>Word: ${param.query} | Field: ${param.field}</h3>
	
	<c:if test="${not empty error}">
	    <div style="color:red; text-align:center;">${error}</div>
	</c:if>
	
	<c:if test="${not empty suggestions}">
        <div>
            <h4>Did you mean:</h4>
            <ul>
                <c:forEach var="s" items="${suggestions}">
                    <li><a href="SearchForSongs?query=${s}&field=${param.field}">${s}</a></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
	
	<c:if test="${empty results}">
	    <p style="text-align:center;">No results found.</p>
	</c:if>
	
	<c:if test="${not empty results}">
	    <table>
	        <thead>
	            <tr>
	                <th>Title</th>
	                <th>Artist</th>
	                <th>Album</th>
	                <th>Lyrics</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="result" items="${results}">
	                <tr>
	                    <td>${result.title}</td>
	                    <td>${result.artist}</td>
	                    <td>${result.album}</td>
	                    <td>
	                        <div class="lyrics-container">
							  <c:choose>
							    <c:when test="${result.truncated}">
							      <span class="short-lyrics">
							        <c:out value="${result.previewLyrics}" />
							      </span>
							      <span class="full-lyrics" style="display: none;">
							        <c:out value="${result.lyrics}" escapeXml="false" />
							      </span>
							      <button class="toggle-button" onclick="toggleLyrics(this)">Show more</button>
							    </c:when>
							    <c:otherwise>
							      <span class="full-lyrics">
							        <c:out value="${result.lyrics}" escapeXml="false" />
							      </span>
							    </c:otherwise>
							  </c:choose>
							</div>
	                    </td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	</c:if>
	
	<p style="text-align:center;">
	    <c:if test="${pageNumber > 1}">
		  <form method="post" action="SearchForSongs">
		    <input type="hidden" name="query" value="${param.query}" />
		    <input type="hidden" name="field" value="${param.field}" />
		    <input type="hidden" name="page" value="${pageNumber - 1}" />
		    <input type="submit" value="Previous Page" />
		  </form>
		</c:if>
	
	    <c:if test="${results.size() == 10}">
		  <form method="post" action="SearchForSongs">
		    <input type="hidden" name="query" value="${param.query}" />
		    <input type="hidden" name="field" value="${param.field}" />
		    <input type="hidden" name="page" value="${pageNumber + 1}" />
		    <input type="submit" value="Next Page" />
		  </form>
		</c:if>
	
	</p>
	
	<div style="text-align:center;">
	    <a href="search.jsp">Search again</a>
	</div>
	
	<script>
	    function toggleLyrics(button) {
	        const container = button.closest('.lyrics-container');
	        const shortLyrics = container.querySelector('.short-lyrics');
	        const fullLyrics = container.querySelector('.full-lyrics');
	        const isCollapsed = fullLyrics.style.display === 'none';
	
	        shortLyrics.style.display = isCollapsed ? 'none' : 'inline';
	        fullLyrics.style.display = isCollapsed ? 'inline' : 'none';
	        button.textContent = isCollapsed ? 'Show less' : 'Show more';
	    }
	</script>

</body>
</html>
