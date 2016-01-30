package com.guru.order.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static SimpleDateFormat sdf_dd_MMM_yy = new SimpleDateFormat(
			"dd-MMM-yy");
	public static SimpleDateFormat sdf_dd_MM_yyyy = new SimpleDateFormat(
			"dd-MM-yyyy");
	
	public static String formatToDDMMMYY(Long timeInMillis) {
		String result = "";
		if (timeInMillis != null) {
			Date date = new Date(timeInMillis);
			result = sdf_dd_MMM_yy.format(date);
		}
		return result;
	}
	
	public static Timestamp getSqlTimeStamp(Calendar cal) {
		Timestamp timeStamp = null;
		if (cal != null) {
			timeStamp = new Timestamp(cal.getTimeInMillis());
		}
		return timeStamp;
	}

	public static Calendar getCalendar(Timestamp timeStamp) {
		Calendar cal = null;
		if (timeStamp != null) {
			cal = getCalendar(timeStamp.getTime());
		}
		return cal;
	}

	public static Calendar getCalendar(Long timeInMillis) {
		Calendar cal = null;
		if (timeInMillis != null) {
			cal = getCalendar(timeInMillis.longValue());
		}
		return cal;
	}

	public static Calendar getCalendar(long timeInMillis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMillis);
		return cal;
	}
	
	public static Calendar parseToDDMMYYYY(String dateTime) throws ParseException {
		Date date = sdf_dd_MM_yyyy.parse(dateTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatToDDMMYYYY(Date date) {
		return sdf_dd_MM_yyyy.format(date);
	}
}
