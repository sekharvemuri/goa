package com.guru.order.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtils {

	public static String join(Collection<String> coll) {
		return join(coll, ",");
	}

	public static String join(Collection<String> coll, String separator) {
		String result = "";
		if (coll != null) {
			StringBuilder sb = new StringBuilder("");
			for (String str : coll) {
				sb.append(separator).append(str);
			}
			result = sb.toString();
			result = result.substring(separator.length());
		}
		return result;
	}
	
	public static String trim(String str) {
		return (str == null) ? null : str.trim();
	}
	
	public static List<String> toList(String str) {
		List<String> list = new ArrayList<String>();
		String[] array = str.split(",");
		for (String s : array) {
			list.add(s.trim());
		}
		return list;
	}

	public static String toUpperCase(String string) {
		return (string == null) ? null : string.toUpperCase();
	}
}
