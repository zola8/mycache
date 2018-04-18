package com.mz.mycache.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.mz.mycache.cache.Cache;
import com.mz.mycache.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService
{

	private static final String ASIA_DUBAI = "Asia/Dubai";

	@Override
	@Cache(valid = 5000)
	public String getTime()
	{
		ZonedDateTime dubaiTime = ZonedDateTime.now(ZoneId.of(ASIA_DUBAI));

		return dubaiTime.format(DateTimeFormatter.ISO_TIME);
	}

}
