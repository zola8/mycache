package com.mz.mycache.util;

import java.time.LocalTime;

public class CacheUtils {

	private CacheUtils() {
		// hidden constructor
	}

	public static boolean isExpired(LocalTime baseTime, int expiration) {
		LocalTime now = LocalTime.now();
		LocalTime time = baseTime.plusSeconds(expiration / 1000);

		return now.isAfter(time);
	}
}
