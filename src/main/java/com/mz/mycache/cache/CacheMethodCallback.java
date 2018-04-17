package com.mz.mycache.cache;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils.MethodCallback;

public class CacheMethodCallback implements MethodCallback {

	@Override
	public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
		if (!method.isAnnotationPresent(Cache.class)) {
			return;
		}

		int valid = method.getAnnotation(Cache.class).valid();

		System.out.println("----- " + valid);

	}

}
