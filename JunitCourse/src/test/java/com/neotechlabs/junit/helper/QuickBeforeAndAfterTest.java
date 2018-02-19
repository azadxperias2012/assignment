package com.neotechlabs.junit.helper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuickBeforeAndAfterTest {
	
	@BeforeClass
	public static void beforeClass() {
		// Initialize static resources
		System.out.println("Before Class");
	}
	
	@Before
	public void setup() {
		System.out.println("Before test");
	}

	@Test
	public void test1() {
		System.out.println("test1 executed");
	}
	
	@Test
	public void test2() {
		System.out.println("test2 executed");
	}
	
	@After
	public void teardown() {
		System.out.println("After test");
	}
	
	@AfterClass
	public static void afterClass() {
		// Clean up static resources
		System.out.println("After Class");
	}

}
