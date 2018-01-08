package com.neotechlabs.thrillio.controllers;

import com.neotechlabs.thrillio.entities.Bookmark;
import com.neotechlabs.thrillio.entities.User;
import com.neotechlabs.thrillio.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}
}
