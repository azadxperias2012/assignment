package com.neotechlabs.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.neotechlabs.thrillio.constants.BookGenre;
import com.neotechlabs.thrillio.constants.KidFriendlyStatus;
import com.neotechlabs.thrillio.constants.MovieGenre;
import com.neotechlabs.thrillio.dao.BookmarkDao;
import com.neotechlabs.thrillio.entities.Book;
import com.neotechlabs.thrillio.entities.Bookmark;
import com.neotechlabs.thrillio.entities.Movie;
import com.neotechlabs.thrillio.entities.User;
import com.neotechlabs.thrillio.entities.UserBookmark;
import com.neotechlabs.thrillio.entities.WebLink;
import com.neotechlabs.thrillio.partner.Shareable;
import com.neotechlabs.thrillio.util.HttpConnect;
import com.neotechlabs.thrillio.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public WebLink createWebLink(long id, String title, String profileUrl, String url, String host) {
		WebLink webLink = new WebLink();
		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setProfileUrl(profileUrl);
		webLink.setUrl(url);
		webLink.setHost(host);

		return webLink;
	}

	public Book createBook(long id, String title, String profileUrl, int publicationYear, String publisher,
			String[] authors, BookGenre genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setProfileUrl(profileUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		if (bookmark instanceof WebLink) {
			try {
				String url = ((WebLink) bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(url);
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		
		System.out.println(
				"Kid-friendly status: " + kidFriendlyStatus + ", marked by " + user.getEmail() + ", " + bookmark);
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);

		System.out.println("Data to be Shared");
		if (bookmark instanceof Shareable) {
			System.out.println(((Shareable) bookmark).getItemData());
		}
	}
}
