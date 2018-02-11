package com.neotechlabs.thrillio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.neotechlabs.thrillio.constants.BookGenre;
import com.neotechlabs.thrillio.constants.Gender;
import com.neotechlabs.thrillio.constants.MovieGenre;
import com.neotechlabs.thrillio.constants.UserType;
import com.neotechlabs.thrillio.entities.Bookmark;
import com.neotechlabs.thrillio.entities.User;
import com.neotechlabs.thrillio.entities.UserBookmark;
import com.neotechlabs.thrillio.entities.WebLink;
import com.neotechlabs.thrillio.managers.BookmarkManager;
import com.neotechlabs.thrillio.managers.UserManager;
import com.neotechlabs.thrillio.util.IOUtil;

public class DataStore {

	private static List<User> users = new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static void loadData() {
//		loadUsers();
//		loadWebLinks();
//		loadMovies();
//		loadBooks();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// OR
			//new com.mysql.jdbc.Driver();
			// OR
			//System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			// OR
			//java.sql.DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			loadUsers(stmt);
			loadWebLinks(stmt);
			loadMovies(stmt);
			loadBooks(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void loadUsers(Statement stmt) throws SQLException {
		String query = "Select * from User";
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			long id = rs.getLong("id");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			int genderId = rs.getInt("gender_id");
			Gender gender = Gender.values()[genderId];
			int user_type_id = rs.getInt("user_type_id");
			UserType userType = UserType.values()[user_type_id];
			
			User user = UserManager.getInstance().createUser(id, email,
					password, firstName, lastName, gender, userType);
			users.add(user);
		}

//		UserManager userManager = UserManager.getInstance();
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "User.txt");
//		for (String row : data) {
//			String[] values = row.split("\t");
//			Gender gender = Gender.MALE;
//			if (values[5].equals("f")) {
//				gender = Gender.FEMALE;
//			} else if (values[5].equals("t")) {
//				gender = Gender.TRANSGENDER;
//			}
//			User user = userManager.createUser(Long.parseLong(values[0]), values[1],
//					values[2], values[3], values[4], gender, UserType.valueOf(values[6]));
//			users.add(user);
//		}
	}

	private static void loadWebLinks(Statement stmt) throws SQLException {
		String query = "Select * from WebLink";
		ResultSet rs = stmt.executeQuery(query);
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		while(rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			String url = rs.getString("url");
			String host = rs.getString("host");
			
			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(id, title, "", url, host);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);

//		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Web-Link.txt");
//		List<Bookmark> bookmarkList = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			Bookmark bookmark = bookmarkManager.createWebLink(Long.parseLong(values[0]), values[1], "", values[2],
//					values[3]);
//			bookmarkList.add(bookmark);
//		}
//		bookmarks.add(bookmarkList);
	}

	private static void loadMovies(Statement stmt) throws SQLException {
		String query = "Select m.id, title, release_year, GROUP_CONCAT(a.name SEPARATOR ',') AS cast, GROUP_CONCAT(d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
				+ " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
				+ "where m.id = ma.movie_id and ma.actor_id = a.id and "
				      + "m.id = md.movie_id and md.director_id = md.id group by m.id";
		ResultSet rs = stmt.executeQuery(query);
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		while(rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");
			int genre_id = rs.getInt("movie_genre_id");
			MovieGenre genre = MovieGenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");
			
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, "", releaseYear, cast, directors, genre, imdbRating);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
		
//		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Movie.txt");
//		List<Bookmark> bookmarkList = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			String[] cast = values[3].split(",");
//			String[] directors = values[4].split(",");
//			Bookmark bookmark = bookmarkManager.createMovie(Long.parseLong(values[0]), values[1], "",
//					Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]), Double.parseDouble(values[6]));
//			bookmarkList.add(bookmark);
//		}
//		bookmarks.add(bookmarkList);
	}

	private static void loadBooks(Statement stmt) throws SQLException {
		String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
				+ " from Book b, Publisher p, Author a, Book_Author ba "
				+ "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
    	ResultSet rs = stmt.executeQuery(query);
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int publicationYear = rs.getInt("publication_year");
			String publisher = rs.getString("name");
			String[] authors = rs.getString("authors").split(",");
			int genre_id = rs.getInt("book_genre_id");
			BookGenre genre = BookGenre.values()[genre_id];
			double amazonRating = rs.getDouble("amazon_rating");
			
			Date createdDate = rs.getDate("created_date");
			System.out.println("createdDate: " + createdDate);
			Timestamp timestamp = rs.getTimestamp(8);
			System.out.println("timestamp: " + timestamp);
			System.out.println("localDateTime: " + timestamp.toLocalDateTime());
			
			System.out.println("id: " + id + ", title: " + title
					+ ", publication year: " + publicationYear +
					", publisher: " + publisher + ", authors: " + String.join(", ", authors)
					+ ", genre: " + genre + ", amazonRating:" + amazonRating);
			
			Bookmark bookmark = BookmarkManager.getInstance().createBook(id,
					title, "", publicationYear, publisher, authors, genre, amazonRating);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
		
//		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Book.txt");
//		List<Bookmark> bookmarkList = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			String[] authors = values[4].split(",");
//			Bookmark bookmark = bookmarkManager.createBook(
//					Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]),
//					values[3], authors, BookGenre.valueOf(values[5]), Double.parseDouble(values[6]));
//			bookmarkList.add(bookmark);
//		}
//		bookmarks.add(bookmarkList);
	}

	public static List<User> getUsers() {
		return users;
	}

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}

}
