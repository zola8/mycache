package com.mz.mycache.util;

import java.time.LocalTime;

/**
 * Utility class for handling cache.
 */
public class CacheUtils
{

	private CacheUtils()
	{
		// hidden constructor
	}

	/**
	 * Checking the expiration.
	 * 
	 * @param baseTime time to check
	 * @param expiration expiration in milliseconds
	 * @return true if expired otherwise false
	 */
	public static boolean isExpired(LocalTime baseTime, int expiration)
	{
		LocalTime now = LocalTime.now();
		LocalTime time = baseTime.plusSeconds(expiration / 1000);

		return now.isAfter(time);
	}
}
