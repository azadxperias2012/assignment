package com.neotechlabs.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeDemo {
	
	public static void main(String[] args) {
		testLegacyDateAPI();
	}

	private static void testLegacyDateAPI() {

		System.out.println("\nDate class ...");
		Date currentDate = new Date();
		System.out.println("currentDate: " + currentDate);
		
		System.out.println("currentDate in ms: " + currentDate.getTime());
		
		System.out.println("\nCalendar class ...");
		// GregorianCalendar(Year, Month (0-11), day (1-31))
		Calendar expiryDate = new GregorianCalendar(2017, 05, 30);
		System.out.println("expiryDate: " + expiryDate);
		System.out.println("expiryDate: " + expiryDate.getTime());
		expiryDate.add(Calendar.MONTH, 8);
		System.out.println("new expiryDate: " + expiryDate.getTime());
		
		expiryDate.roll(Calendar.MONTH, 11);
		System.out.println("new expiryDate: " + expiryDate.getTime());
		
		// Time Zone Demo
		System.out.println("\nTimeZones ...");
		String[] timeZones = TimeZone.getAvailableIDs();
		for (String timeZone : timeZones) {
			System.out.println(timeZone);
		}
		// no-arg constructor below ==> default timezone		
		Calendar gameStartTime = new GregorianCalendar(TimeZone.getTimeZone("Europe/London"));
		gameStartTime.set(2017, Calendar.JANUARY, 03, 9, 00);
		System.out.println("gameStartTime: " + gameStartTime);
		System.out.println("gameStartTime.getTime: " + gameStartTime.getTime());
		System.out.println("London time -- MONTH/DAY at hr:min:sec (AM/PM) -- "
				+ (gameStartTime.get(Calendar.MONTH) + 1) + "/" + gameStartTime.get(Calendar.DATE)
				+ " at " + gameStartTime.get(Calendar.HOUR) + ":" + gameStartTime.get(Calendar.MINUTE)
				+ " " + (gameStartTime.get(Calendar.AM_PM) == 0 ? "(AM)" : "(PM)"));
		
		gameStartTime.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Indian time -- MONTH/DAY at hr:min:sec (AM/PM) -- "
				+ (gameStartTime.get(Calendar.MONTH) + 1) + "/" + gameStartTime.get(Calendar.DATE)
				+ " at " + gameStartTime.get(Calendar.HOUR) + ":" + gameStartTime.get(Calendar.MINUTE)
				+ " " + (gameStartTime.get(Calendar.AM_PM) == 0 ? "(AM)" : "(PM)"));
		
		gameStartTime.setTimeZone(TimeZone.getTimeZone("GMT-08:30"));
		System.out.println("Custom time -- MONTH/DAY at hr:min:sec (AM/PM) -- "
				+ (gameStartTime.get(Calendar.MONTH) + 1) + "/" + gameStartTime.get(Calendar.DATE)
				+ " at " + gameStartTime.get(Calendar.HOUR) + ":" + gameStartTime.get(Calendar.MINUTE)
				+ " " + (gameStartTime.get(Calendar.AM_PM) == 0 ? "(AM)" : "(PM)"));
		
		// Day Light Savings Time (DST): Change Calendar.JANUARY to Calendar.JULY
		// GMT would be 8 and London would be 9
		// UK observes DST from March to October (British Summer Time)
		
		Calendar gameFinal = new GregorianCalendar(TimeZone.getTimeZone("Europe/London"));
		gameFinal.set(2017, Calendar.JULY, 16, 9, 00);
		System.out.println("After? " + gameStartTime.after(gameFinal));
		System.out.println("Before? " + gameStartTime.before(gameFinal));
	}

}
