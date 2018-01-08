package com.neotechlabs.thrillio.dao;

import com.neotechlabs.thrillio.DataStore;
import com.neotechlabs.thrillio.entities.Bookmark;
import com.neotechlabs.thrillio.entities.UserBookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
}
