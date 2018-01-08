package com.neotechlabs.thrillio.entities;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import com.neotechlabs.thrillio.constants.MovieGenre;
import com.neotechlabs.thrillio.managers.BookmarkManager;

class MovieTest {

	@Test
	void testIsKidFriendlyEligible() {
		BookmarkManager bookmarkManager = BookmarkManager.getInstance();
		
		// Test 1 - For Horror Genre
		Movie movie = bookmarkManager.createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		boolean iskidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("For Horror Genre - iskidFriendlyEligible() should return false", iskidFriendlyEligible);
		
		// Test 2 - For Thrillers Genre
		movie = bookmarkManager.createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);
		iskidFriendlyEligible = movie.isKidFriendlyEligible();
		assertFalse("For Thrillers Genre - iskidFriendlyEligible() should return false", iskidFriendlyEligible);
	}

}
