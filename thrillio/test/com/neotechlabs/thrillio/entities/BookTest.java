package com.neotechlabs.thrillio.entities;

import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;

import com.neotechlabs.thrillio.constants.BookGenre;
import com.neotechlabs.thrillio.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		BookmarkManager bookmarkManager = BookmarkManager.getInstance();

		// Test 1 - For Philosophy Genre
		Book book = bookmarkManager.createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		boolean iskidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("For Philosophy Genre - iskidFriendlyEligible() should return false", iskidFriendlyEligible);

		// Test 2 - For Self help Genre
		book = bookmarkManager.createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		iskidFriendlyEligible = book.isKidFriendlyEligible();
		assertFalse("For Self help Genre - iskidFriendlyEligible() should return false", iskidFriendlyEligible);
	}

}
