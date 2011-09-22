package org.cloudbackup;

public class ValueUtils {

	public static String get(String value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public static Boolean booleanFromString(String value) {
		if (value == null) {
			return null;
		}
		value = value.trim().toLowerCase();
		return value.equals("yes") || value.equals("1") || value.equals("y") || value.equals("sim") || value.equals("s");
	}

	public static boolean booleanFromString(String value, boolean defaultValue) {
		Boolean result = booleanFromString(value);
		if (result == null) {
			return defaultValue;
		}
		return result;
	}

	public static String toString(Boolean value) {
		if (value == null) {
			return null;
		}
		return value ? "yes" : "no";
	}

	public static Integer integerFromString(String value) {
		if (value == null) {
			return null;
		}
		try {
			return Integer.parseInt(value, 10);
		} catch (Exception e) {
			return null;
		}
	}

}
