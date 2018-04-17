package com.mz.mycache.cache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;

import com.mz.mycache.service.CacheService;

@Component
public class CacheAnnotationProcessor implements BeanPostProcessor {

	@Autowired
	private CacheService<String> cacheService;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		scanMyCacheAnnotation(bean, beanName);

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	private void scanMyCacheAnnotation(Object bean, String beanName) {
		Class<?> managedBeanClass = bean.getClass();
		MethodCallback methodCallback = new CacheMethodCallback(cacheService);

		ReflectionUtils.doWithMethods(managedBeanClass, methodCallback);
	}

}
