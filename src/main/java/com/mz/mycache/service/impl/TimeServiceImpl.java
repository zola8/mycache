package com.mz.mycache.service.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mz.mycache.cache.Cache;
import com.mz.mycache.cache.data.CacheMetaData;
import com.mz.mycache.service.CacheService;
import com.mz.mycache.service.TimeService;
import com.mz.mycache.util.CacheUtils;

@Service
public class TimeServiceImpl implements TimeService {

	private static final String ASIA_DUBAI = "Asia/Dubai";
	private static final String METHOD_NAME = TimeServiceImpl.class.getCanonicalName() + "." + "getTime";
	private static final Logger LOGGER = LoggerFactory.getLogger(TimeServiceImpl.class);

	@Autowired
	private CacheService<String> cacheService;

	@Override
	@Cache(valid = 5000)
	public String getTime() {
		CacheMetaData<String> metaData = cacheService.getValue(METHOD_NAME);

		if (CacheUtils.isExpired(metaData.getTime(), metaData.getExpiration()) || metaData.getData() == null) {
			ZonedDateTime dubaiTime = ZonedDateTime.now(ZoneId.of(ASIA_DUBAI));
			String data = dubaiTime.format(DateTimeFormatter.ISO_TIME);

			cacheService.update(METHOD_NAME, data);
			LOGGER.info("--- {} not found any data in cache. Time={}", METHOD_NAME, LocalTime.now());

			return data;
		}

		LOGGER.info("--- {} found data in cache :)", METHOD_NAME);
		return metaData.getData();
	}

}
