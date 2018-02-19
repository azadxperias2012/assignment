package com.neotechlabs.junit.helper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickBeforeAndAfterTest {
	
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

}
