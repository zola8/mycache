package com.mz.mycache.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.mz.mycache.cache.data.CacheMetaData;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceImplTest
{
	private static final String NAME = "name";
	private static final String VALUE = "value";
	private static final int EXPIRATION = 1000;

	@InjectMocks
	private CacheServiceImpl<String> cacheService;

	@Before
	public void init()
	{
		cacheService.clear();
	}

	@Test
	public void registerValue_nullCheck()
	{
		// WHEN
		cacheService.registerValue(null, EXPIRATION, VALUE);

		// THEN
		assertNull(cacheService.getValue(null));
	}

	@Test
	public void registerValue_success()
	{
		// WHEN
		cacheService.registerValue(NAME, EXPIRATION, VALUE);

		// THEN
		CacheMetaData<String> result = cacheService.getValue(NAME);

		assertNotNull(result);
		assertEquals(EXPIRATION, result.getExpiration());
		assertEquals(VALUE, result.getData());
	}

	@Test
	public void double_registration_cause_no_problem_and_used_first_one()
	{
		// WHEN
		cacheService.registerValue(NAME, EXPIRATION, VALUE);
		cacheService.registerValue(NAME, EXPIRATION + 1000, VALUE + "abc");

		// THEN
		CacheMetaData<String> result = cacheService.getValue(NAME);

		assertNotNull(result);
		assertEquals(EXPIRATION, result.getExpiration());
		assertEquals(VALUE, result.getData());
	}

	@Test
	public void clear_everything()
	{
		// GIVEN
		cacheService.registerValue(NAME, EXPIRATION, VALUE);
		cacheService.registerValue("name2", EXPIRATION, VALUE);
		assertNotNull(cacheService.getValue(NAME));
		assertNotNull(cacheService.getValue("name2"));

		// WHEN
		cacheService.clear();

		// THEN
		assertNull(cacheService.getValue(NAME));
		assertNull(cacheService.getValue("name2"));
	}

	@Test
	public void updates_change_time_and_data()
	throws InterruptedException
	{
		// GIVEN
		cacheService.registerValue(NAME, EXPIRATION, VALUE);
		String oldData = cacheService.getValue(NAME).getData();
		LocalTime oldTime = cacheService.getValue(NAME).getTime();

		// WHEN
		Thread.sleep(100L);
		cacheService.update(NAME, "newValue");

		// THEN
		CacheMetaData<String> result = cacheService.getValue(NAME);

		assertNotEquals(oldData, result.getData());
		assertNotEquals(oldTime, result.getTime());
	}

}
