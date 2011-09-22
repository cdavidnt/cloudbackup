package org.cloudbackup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Dates {

	private GregorianCalendar calendar = new GregorianCalendar();

	public Dates() {
	}

	public Dates(Date date) {
		this.calendar.setTime(date);
	}

	public Dates(GregorianCalendar calendar) {
		this.calendar = calendar;
	}

	public static Dates now() {
		return new Dates();
	}

	public static Dates at(Date date) {
		return new Dates(date);
	}

	public static Dates at(GregorianCalendar calendar) {
		return new Dates(calendar);
	}

	public static Dates at(int day, int month, int year) {
		return Dates.now().day(day).month(month).year(year);
	}

	public static Dates at(int day, int month) {
		return Dates.now().day(day).month(month);
	}

	public Dates addDays(int days) {
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return this;
	}

	public Dates addMonths(int months) {
		calendar.add(Calendar.MONTH, months);
		return this;
	}

	public Dates addYears(int years) {
		calendar.add(Calendar.YEAR, years);
		return this;
	}

	public Dates firstDayOfMonth() {
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return this;
	}

	public Dates firstDayOfMonth(int month) {
		firstDayOfMonth();
		calendar.set(Calendar.MONTH, 1);
		return this;
	}

	public Dates firstDayOfMonthOfYear(int year) {
		firstDayOfMonth();
		calendar.set(Calendar.YEAR, year);
		return this;
	}

	public Dates firstDayOfMonthOfYear(int month, int year) {
		firstDayOfMonth(month);
		calendar.set(Calendar.YEAR, year);
		return this;
	}

	public Dates firstDayOfYear(int year) {
		firstDayOfMonth(1);
		calendar.set(Calendar.YEAR, year);
		return this;
	}

	public Dates firstDayOfYear() {
		firstDayOfMonth(1);
		return this;
	}

	public Dates date(int day, int month, int year) {
		day(day);
		month(month);
		year(year);
		return this;
	}

	public Dates date(int day, int month) {
		day(day);
		month(month);
		return this;
	}

	public Dates year(int year) {
		calendar.set(Calendar.YEAR, year);
		return this;
	}

	public Dates month(int month) {
		calendar.set(Calendar.MONTH, month - 1);
		return this;
	}

	public Dates day(int day) {
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return this;
	}

	public Dates midday() {
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return this;
	}

	public Date date() {
		return calendar.getTime();
	}

	public GregorianCalendar calendar() {
		return (GregorianCalendar) calendar.clone();
	}

	public Dates previousYear() {
		addYears(-1);
		return this;
	}

}
