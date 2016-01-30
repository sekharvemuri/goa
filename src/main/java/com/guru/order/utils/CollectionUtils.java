package com.guru.order.utils;

import java.util.Collection;

public class CollectionUtils {

	public static boolean isEmpty(Collection<Object> coll) {
		return (coll == null || coll.isEmpty()) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static boolean isNotEmpty(Collection<Object> coll) {
		return !isEmpty(coll);
	}
	
}
