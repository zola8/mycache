package com.mz.mycache.service;

import com.mz.mycache.cache.data.CacheMetaData;

public interface CacheService<T> {

	void registerValue(String methodName, int expiration, T value);

	CacheMetaData<T> getValue(String methodName);

	void update(String methodName, T data);

}
