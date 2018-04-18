package com.mz.mycache.cache;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils.MethodCallback;

import com.mz.mycache.service.CacheService;

public class CacheMethodCallback implements MethodCallback
{

	private CacheService<String> cacheService;

	public CacheMethodCallback(CacheService<String> cacheService)
	{
		this.cacheService = cacheService;
	}

	@Override
	public void doWith(Method method)
	throws IllegalArgumentException, IllegalAccessException
	{
		if (!method.isAnnotationPresent(Cache.class))
		{
			return;
		}

		String methodName = method.getDeclaringClass().getCanonicalName() + "." + method.getName();
		int valid = method.getAnnotation(Cache.class).valid();

		cacheService.registerValue(methodName, valid, null);
	}

}
