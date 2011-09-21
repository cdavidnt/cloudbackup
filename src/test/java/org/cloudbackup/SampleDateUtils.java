package org.cloudbackup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SampleDateUtils {

	public static Date date(int day) {
		GregorianCalendar today = new GregorianCalendar();
		return new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), day).getTime();
	}

	public static Date date(Date date) {
		return date;
	}

	public static Date date(int day, int month) {
		return new GregorianCalendar(new GregorianCalendar().get(Calendar.YEAR), month - 1, day).getTime();
	}

	public static Date date(int day, int month, int year) {
		if (year < 0) {
			year = new GregorianCalendar().get(Calendar.YEAR) + year;
		}
		return new GregorianCalendar(year, month - 1, day).getTime();
	}

	public static Date firstDayOfYear() {
		return date(1, 1);
	}

	public static Date firstDayOfMonth() {
		return date(1);
	}

	public static Date firstDayOfMonth(int month) {
		return date(1, month - 1);
	}

	public static Date now() {
		return new GregorianCalendar().getTime();
	}
}
