<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/styling.css">
<meta charset="ISO-8859-1">
<title>Song Search</title>
<style>
	h1 {
        text-align: center;
      }
      form {
        text-align: center;
      }
</style>
</head>
<body>
	<h1>Search Song</h1>
	<c:if test="${not empty sessionScope.history}">
	  <div class="history-section">
	    <h3>Your Recent Searches:</h3>
	    <ul>
	      <c:forEach var="q" items="${sessionScope.history}">
	        <li><a href="SearchForSongs?query=${q}&field=all">${q}</a></li>
	      </c:forEach>
	    </ul>
	    <form method="post" action="ClearHistory">
	      <input type="submit" value="Clear History" />
	    </form>
	  </div>
	</c:if>
	
    <form method=post action=SearchForSongs>
    	<select name=field id=field>
        	<option value="all">All Fields</option>
		    <option value="title">Title</option>
		    <option value="album">Album</option>
		    <option value="artist">Artist</option>
		    <option value="lyrics">Lyrics</option>
		 </select>
        <input type=text name=query id=query/><br>
        
		 <input type="hidden" name="pageNumber" value="1">
        <input type=submit value=Search name=results>
    </form>
    
    <form id="surpriseForm" method="post" action="SearchForSongs" style="text-align: center;">
	  <input type="hidden" name="query" id="surpriseQuery" value="" />
	  <input type="hidden" name="field" value="all" />
	  <input type="submit" value=" Surprise Me!" onclick="return surpriseMe()" />
	</form>
	
	<script>
	  function surpriseMe() {
	    const words = ["love", "night", "dream", "you", "baby", "fire", "heart", "dance", "sky", "rain"];
	    const random = words[Math.floor(Math.random() * words.length)];
	    document.getElementById("surpriseQuery").value = random;
	    return true;
	  }
	</script>
	
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>
</body>
</html>