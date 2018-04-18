package com.mz.mycache.service.impl;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mz.mycache.cache.data.CacheMetaData;
import com.mz.mycache.service.CacheService;

@Service
public class CacheServiceImpl<T> implements CacheService<T>
{

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

	private Map<String, CacheMetaData<T>> map = new HashMap<>();

	@Override
	public void registerValue(String methodName, int expiration, T value)
	{
		if (methodName == null)
		{
			LOGGER.warn("Invalid method name.");

			return;
		}

		if (map.containsKey(methodName))
		{
			LOGGER.warn("Already registered ?!");

			return;
		}

		LocalTime time = LocalTime.now();
		CacheMetaData<T> metaData = new CacheMetaData<T>(expiration, time, value);

		map.put(methodName, metaData);
		LOGGER.info("--- {} registered in cache service at {}", methodName, time);
	}

	@Override
	public CacheMetaData<T> getValue(String methodName)
	{
		return map.get(methodName);
	}

	@Override
	synchronized public void update(String methodName, T data)
	{
		CacheMetaData<T> value = getValue(methodName);

		if (value == null)
		{
			throw new RuntimeException("Method is not annotated or registered correctly: " + methodName);
		}

		value.setData(data);
		value.setTime(LocalTime.now());
	}

	@Override
	public void clear()
	{
		map.clear();
	}

}
