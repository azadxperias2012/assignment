package com.neotechlabs.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeDemo {
	
	public static void main(String[] args) {
		//testLegacyDateAPI();
		testDateTimeAPI();
	}

	private static void testDateTimeAPI() {
		// Use-case 1: Software renewal
		LocalDate expiryDate = LocalDate.of(2017, Month.JUNE, 30);
		System.out.println("expiryDate: " + expiryDate);
		
		// Other methods: plus & minus methods, isBefore, isAfter
		LocalDate newExpiryDate = expiryDate.plusMonths(8);
		System.out.println("newExpiryDate: " + newExpiryDate);
		System.out.println("\nYear: " + newExpiryDate.getYear());
		System.out.println("Month: " + newExpiryDate.getMonth());
		System.out.println("day of month: " + newExpiryDate.getDayOfMonth());
		System.out.println("day of week: " + newExpiryDate.getDayOfWeek());
		System.out.println("Leap Year? " + newExpiryDate.isLeapYear());
		System.out.println("length of month: " + newExpiryDate.lengthOfMonth());
		
		// get returns int
		System.out.println("Year again: " + newExpiryDate.get(ChronoField.YEAR));
		System.out.println("Month again: " + newExpiryDate.get(ChronoField.MONTH_OF_YEAR));
		System.out.println("day of month again: " + newExpiryDate.get(ChronoField.DAY_OF_MONTH));
		
		// parse string
		LocalDate epoch = LocalDate.parse("1970-01-01"); // yyyy-mm-dd
		System.out.println("epoch year: " + epoch.getYear());
		
		// Use-case 2: Game start time localized to time zone
		LocalTime time = LocalTime.of(9, 00, 00);
		System.out.println("\ntime-hr: " + time.getHour());
		
		LocalDate gameStartDate = LocalDate.of(2017, Month.JULY, 03);
		LocalDateTime gameStartTime = LocalDateTime.of(gameStartDate, time);
		System.out.println("gameStartDateTime: " + gameStartTime);
		
		// TimeZone ==> ZoneId
		ZonedDateTime zonedGameStartTime = ZonedDateTime.of(gameStartTime, ZoneId.of("Europe/London"));
		System.out.println("zonedGameStartTime: " + zonedGameStartTime);
		
		ZonedDateTime gmt = zonedGameStartTime.withZoneSameInstant(ZoneId.of("GMT"));
		System.out.println("gmt: " + gmt);
		
		ZonedDateTime indiaTime = zonedGameStartTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
		System.out.println("indiaTime: " + indiaTime);
		
		ZonedDateTime pst = zonedGameStartTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
		System.out.println("pst: " + pst);
		
		// Use-case 3: Age calculation (Period)
		LocalDate birthDate = LocalDate.of(1989, Month.JULY, 21);
		LocalDate today = LocalDate.now();
		Period period = birthDate.until(today);
		System.out.println("\nComplete Age: " + period.toString());
		System.out.println("Age: " + period.getYears());
		
		// Use-case 4: Interval timing (Instant & Duration)
		Instant startTime = Instant.now();
		testLegacyDateAPI();
		Instant endTime = Instant.now();
		Duration timeElapsed = Duration.between(startTime, endTime);
		System.out.println("timeElapsed in ms:" + timeElapsed.toMillis());
		
		// Partial Classes
		System.out.println("Christmas: " + MonthDay.of(Month.DECEMBER, 25));
		System.out.println("Credit card expiry date: " + YearMonth.of(2022, Month.FEBRUARY));
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
