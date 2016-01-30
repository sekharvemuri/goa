package com.guru.order.utils;

import java.util.Collection;

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
	
}
