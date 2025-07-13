<p align="center">
  <img src="./Logo.jpeg" alt="Preview" width="200"/>
</p>

# <p align="center"> **SongSearch**</p>

**SongSearch** is a Java web application that allows users to search through thousands of song lyrics, titles, artists, and albums using Apache Lucene.

---

## Features

-  Full-text search using **Apache Lucene**
-  Supports multiple fields: `lyrics`, `title`, `artist`, `album`, or all
-  Highlighted search terms (bold & purple)
-  Clickable lyrics: Show preview → Expand to full
-  "Surprise Me" button generates random queries
-  Pagination with "Next" and "Previous" buttons
- **Recent search history per user** (stored in session)  
  <br>
  - Displayed on the main page  
  - Clickable for quick re-search  
  - Clearable via a “Clear History” button

- Smart query suggestions based on your own recent searches

---

## How It Works

- **Indexing** is done from a CSV using `SongExcel.java`, which reads song data and creates a Lucene index. The output files are already inside the `indexedFiles` folder.
- **Search** is handled by `SongSearch.java`, which uses Lucene queries and highlights matches. Someone can use a main class here to search through songs, without using the Server and the frontend.
- **Frontend** uses JSPs (`search.jsp`, `result.jsp`, `nextpage.jsp`) and styled with `styling.css`.

---

## Technologies Used

- Java 11
- Apache Lucene 9.x
- Maven
- JSP / Servlets
- Tomcat 9
- HTML, CSS

---

## **Run tests**
All tests are stored within the test folder. To execute all of them simply run:
```sh
./mvnw test
```
Or run individual test classes like:
<pre>Right-click on SearchForSongsTest.java > Run As > JUnit Test</pre>

## How to Run

1. Clone or download the project
2. Import into Eclipse/IntelliJ as a **Maven Web App**
3. Make sure your Tomcat uses **Java 11**
4. Optionally run `SongExcel.java` once to build the index:
   ```
   C:/Users/.../indexedFiles/
   ```
   It already exists in the project.
5. Deploy to Tomcat and visit `http://localhost:8080/songsearch/SearchForSongs`

---

## License

This project is for educational and non-commercial use.

---

## **Contributors**
- Themistokleia Siakavara
