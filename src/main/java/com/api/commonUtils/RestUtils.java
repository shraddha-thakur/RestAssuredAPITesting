package com.api.commonUtils;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String userName() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		return ("John"+generatedString);
	}

	public static String userJob() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return ("Test"+generatedString);
	}

	public static String salary() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}

	public static String age() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}

	public static String randomemail() {
		String generatedString = RandomStringUtils.randomAlphanumeric(10) + "@" + RandomStringUtils.randomAlphanumeric(3) + ".com";
		return (generatedString);
	}
}
