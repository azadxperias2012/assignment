package com.neotechlabs.thrillio;

import com.neotechlabs.thrillio.entities.Bookmark;
import com.neotechlabs.thrillio.entities.User;
import com.neotechlabs.thrillio.managers.BookmarkManager;
import com.neotechlabs.thrillio.managers.UserManager;

public class Lauch {
	
	private static User[] users;
	private static Bookmark[][] bookmarks;

	private static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		
		System.out.println("2. Printing data ...");
		printUserData();
		printBookmarkData();
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	public static void main(String[] args) {
		loadData();
	}

}
