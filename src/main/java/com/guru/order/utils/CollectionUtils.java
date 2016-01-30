package com.guru.order.utils;

import java.util.Collection;

public class CollectionUtils {

	public static <E> boolean isEmpty(Collection<? extends E> coll) {
		return (coll == null || coll.isEmpty()) ? Boolean.TRUE : Boolean.FALSE;
	}

	public static <E> boolean isNotEmpty(Collection<? extends E> coll) {
		return !isEmpty(coll);
	}

}
