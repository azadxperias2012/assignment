package com.neotechlabs.junit.helper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({StringHelperTest.class, StringHelperParameterizedTest.class})
public class StringHelperTestSuite {

}
