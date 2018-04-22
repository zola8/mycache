package com.mz.mycache.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mz.mycache.cache.data.CacheMetaData;
import com.mz.mycache.service.CacheService;
import com.mz.mycache.service.impl.CacheServiceImpl;
import com.mz.mycache.util.CacheUtils;

@Aspect
@Component
public class CacheAspect
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Autowired
	private CacheService<String> cacheService;

	@Around("@annotation(com.mz.mycache.cache.Cache)")
	public Object checkCache(ProceedingJoinPoint joinPoint)
	throws Throwable
	{
		String actualMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		CacheMetaData<String> metaData = cacheService.getValue(actualMethod);

		if (CacheUtils.isExpired(metaData.getTime(), metaData.getExpiration()) || metaData.getData() == null)
		{
			String data = (String) joinPoint.proceed();
			cacheService.update(actualMethod, data);

			LOGGER.info("--- {} not found any data in cache -> original method called.", actualMethod);
			return data;
		}

		LOGGER.info("--- {} found data in cache :)", actualMethod);
		return metaData.getData();
	}

}
