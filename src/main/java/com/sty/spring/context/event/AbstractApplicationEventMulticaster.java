package com.sty.spring.context.event;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.BeanFactory;
import com.sty.spring.beans.factory.BeanFactoryAware;
import com.sty.spring.context.ApplicationEvent;
import com.sty.spring.context.ApplicationListener;
import com.sty.spring.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
	
	public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
	
	private BeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	@Override
	public void addApplicationListener(ApplicationListener<?> listener) {
		applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
	}
	
	@Override
	public void removeApplicationListener(ApplicationListener<?> listener) {
		applicationListeners.remove(listener);
	}
	
	protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
		LinkedList<ApplicationListener> allListeners = new LinkedList<>();
		for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
			if (supportsEvent(applicationListener, event)) {
				allListeners.add(applicationListener);
			}
		}
		return allListeners;
	}
	
	private boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
		Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
		Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
		Type genericInterface = targetClass.getGenericInterfaces()[0];
		
		Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
		String className = actualTypeArgument.getTypeName();
		Class<?> eventClassName;
		try {
			eventClassName = Class.forName(className);
		}catch (ClassNotFoundException e) {
			throw new BeansException("wrong event class name: " + className);
		}
		return eventClassName.isAssignableFrom(event.getClass());
	}
}
