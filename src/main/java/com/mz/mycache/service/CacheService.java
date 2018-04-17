package com.mz.mycache.service;

import com.mz.mycache.cache.data.CacheMetaData;

/**
 *
 * Common cache service.
 *
 * @param <T>
 *            type
 */
public interface CacheService<T> {

	/**
	 * Register method to the cache.
	 * 
	 * @param methodName
	 *            canonical name
	 * @param expiration
	 *            expiration in milliseconds
	 * @param value
	 *            data
	 */
	void registerValue(String methodName, int expiration, T value);

	/**
	 * Get value from the cache.
	 * 
	 * @param methodName
	 *            key
	 * @return data
	 */
	CacheMetaData<T> getValue(String methodName);

	/**
	 * Updates the cache.
	 * 
	 * @param methodName
	 *            key
	 * @param data
	 *            data
	 */
	void update(String methodName, T data);

}
