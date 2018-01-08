package com.neotechlabs.thrillio.dao;

import com.neotechlabs.thrillio.DataStore;
import com.neotechlabs.thrillio.entities.Bookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}
}
