package com.neotechlabs.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import com.neotechlabs.thrillio.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidFriendlyEligible() {
		BookmarkManager bookmarkManager = BookmarkManager.getInstance();

		// Test 1: adult in host -- false
		WebLink webLink = bookmarkManager.createWebLink(2000, "Taming Tiger, Part 2", "",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.adult.com");
		boolean iskidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertFalse("For adult in host - iskidFriendlyEligible() must return false", iskidFriendlyEligible);
		
		// Test 2: adult in url, but not in host part -- true
		webLink = bookmarkManager.createWebLink(2000, "Taming Tiger, Part 2", "",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html", "http://www.javaworld.com");
		iskidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertTrue("For adult in url, but not in host part - iskidFriendlyEligible() must return true", iskidFriendlyEligible);

		// Test 3: adult in title only -- true
		webLink = bookmarkManager.createWebLink(2000, "Taming adult, Part 2", "",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
		iskidFriendlyEligible = webLink.isKidFriendlyEligible();
		assertTrue("For adult in title only - iskidFriendlyEligible() must return true", iskidFriendlyEligible);
	}

}
