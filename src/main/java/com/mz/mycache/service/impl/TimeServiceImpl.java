package com.mz.mycache.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.mz.mycache.cache.Cache;
import com.mz.mycache.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService {

	@Override
	@Cache(valid=30000)
	public String getTime() {
		ZonedDateTime dubaiTime = ZonedDateTime.now(ZoneId.of("Asia/Dubai"));

		return dubaiTime.format(DateTimeFormatter.ISO_TIME);
	}

}
