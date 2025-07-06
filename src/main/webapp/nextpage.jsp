<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Results</title>
<style>
	h1{
		text-align: center;
	}
	form{
		text-align: center;
	}
</style>
<meta charset="ISO-8859-1">
<style>
    .highlight {
      font-weight: bold;
      color: purple;
    }
  </style>
</head>
	<link rel="stylesheet" type="text/css" href="css/styling.css">
<body>

	<h1>Song Search Results</h1>
	<h3> Word: ${param.query} Field: ${param.field}</h3>
	<c:if test="${not empty error}">
		<div style="color:red">${error}</div>
	</c:if>
	
	<c:if test="${empty results}">
		<p>No results found.</p>
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
						<td>${result.title.replaceAll(query, "<span class='highlight'>$0</span>")}</td>
						<td>${result.artist.replaceAll(query, "<span class='highlight'>$0</span>")}</td>
						<td>${result.album}</td>
						<td>${result.lyrics.replaceAll(query, "<span class='highlight'>$0</span>")}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<p>
	<c:if test="${pageNumber > 1}">
		<button onclick="history.back()"> Previous Page</button>
	</c:if>
	<c:if test="${results.size() == 10}">
	  	<button onclick="history.forward()">Next Page</button>
	  </c:if>
	</p>
	<br>
	<a href="search.jsp">Search again</a>
</body>
</html>