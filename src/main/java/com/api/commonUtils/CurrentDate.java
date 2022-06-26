package com.api.commonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class CurrentDate {

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String newdate = dateformat.format(cal.getTime());

		return newdate;
	}

}
