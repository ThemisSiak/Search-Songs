# SongSearch

**SongSearch** is a Java web application that allows users to search through thousands of song lyrics, titles, artists, and albums using Apache Lucene. The project features a web interface with highlighting, pagination, and interactive lyric previews — all styled with a playful pink theme.

---

## Features

- Full-text search using **Apache Lucene**
-  Supports multiple fields: `lyrics`, `title`, `artist`, `album`, or all
-  Highlighted search terms (bold & purple)
-  Clickable lyrics: Show preview → Expand to full
-  "Surprise Me" button generates random queries
-  Pagination with "Next" and "Previous" buttons

---

## How It Works

- **Indexing** is done from a CSV using `SongExcel.java`, which reads song data and creates a Lucene index.
- **Search** is handled by `SongSearch.java`, which uses Lucene queries and highlights matches.
- **Frontend** uses JSPs (`search.jsp`, `result.jsp`) and styled with `styling.css`.

---

## Technologies Used

- Java 11
- Apache Lucene 9.x
- Maven
- JSP / Servlets
- Tomcat 9
- HTML, CSS

---

## Folder Structure

```
songsearch/
├── src/
│   └── main/
│       ├── java/com/example/songs/
│       │   ├── SongSearch.java
│       │   ├── SongExcel.java
│       │   ├── SongData.java
│       │   └── SongDocument.java
│       └── webapp/
│           ├── search.jsp
│           ├── result.jsp
│           └── css/styling.css
├── inputFile/spotify_millsongdata.csv
├── indexedFiles/           <-- Output Lucene index
└── pom.xml
```

---

## How to Run

1. Clone or download the project
2. Import into Eclipse/IntelliJ as a **Maven Web App**
3. Make sure your Tomcat uses **Java 11**
4. Run `SongExcel.java` once to build the index:
   ```
   C:/Users/.../indexedFiles/
   ```
5. Deploy to Tomcat and visit `http://localhost:8080/songsearch/SearchForSongs`

---

## License

This project is for educational and non-commercial use.

---

Made by 
Themistokleia Siakavara
