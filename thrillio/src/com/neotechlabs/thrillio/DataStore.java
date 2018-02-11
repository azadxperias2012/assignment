package com.neotechlabs.thrillio;

import java.util.ArrayList;
import java.util.List;

import com.neotechlabs.thrillio.constants.BookGenre;
import com.neotechlabs.thrillio.constants.Gender;
import com.neotechlabs.thrillio.constants.MovieGenre;
import com.neotechlabs.thrillio.constants.UserType;
import com.neotechlabs.thrillio.entities.Bookmark;
import com.neotechlabs.thrillio.entities.User;
import com.neotechlabs.thrillio.entities.UserBookmark;
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
		
	}

	private static void loadUsers() {
		UserManager userManager = UserManager.getInstance();
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User.txt");
		for (String row : data) {
			String[] values = row.split("\t");
			Gender gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}
			User user = userManager.createUser(Long.parseLong(values[0]), values[1],
					values[2], values[3], values[4], gender, UserType.valueOf(values[6]));
			users.add(user);
		}
	}

	private static void loadWebLinks() {
		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Web-Link.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = bookmarkManager.createWebLink(Long.parseLong(values[0]), values[1], "", values[2],
					values[3]);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] cast = values[3].split(",");
			String[] directors = values[4].split(",");
			Bookmark bookmark = bookmarkManager.createMovie(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]), Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadBooks() {
		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			Bookmark bookmark = bookmarkManager.createBook(
					Long.parseLong(values[0]), values[1], "", Integer.parseInt(values[2]),
					values[3], authors, BookGenre.valueOf(values[5]), Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
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
