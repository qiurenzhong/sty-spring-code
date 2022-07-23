package com.sty.spring.context.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.ConfigurableListableBeanFactory;
import com.sty.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.sty.spring.beans.factory.config.BeanPostProcessor;
import com.sty.spring.context.ApplicationEvent;
import com.sty.spring.context.ApplicationListener;
import com.sty.spring.context.ConfigurableApplicationContext;
import com.sty.spring.context.event.ApplicationEventMulticaster;
import com.sty.spring.context.event.ContextClosedEvent;
import com.sty.spring.context.event.ContextRefreshedEvent;
import com.sty.spring.context.event.DefaultApplicationEventMulticaster;
import com.sty.spring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 抽象应用上下文实现
 *
 * @author tianma
 * @date 2022/6/30
 * @version 1.0.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
	
	public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
	
	private ApplicationEventMulticaster applicationEventMulticaster;
	
	@Override
	public void refresh() throws BeansException {
		// 1.创建BeanFactory 并加载 BeanDefinition
		refreshBeanFactory();
		
		//2.获取BeanFactory
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		
		//3.在bean实例之前，执行BeanFactoryPostProcessor
		invokeBeanFactoryPostProcessor(beanFactory);
		
		//4.添加ApplicationContextAwareProcessor，让继承ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
		
		//5.BeanPostProcessor需要提前于其他bean对象实例化之前执行注册操作
		registerBeanPostProcessor(beanFactory);
		
		//6.初始化事件发布者
		initApplicationEventMulticaster();
		
		//7.注册事件监听器
		registerListeners();
		
		//.8提前实例化单例bean对象
		beanFactory.preInstantiateSingletons();
		
		//9.发布容器刷新完成事件
		finishRefresh();
	}
	
	private void finishRefresh() {
		publishEvent(new ContextRefreshedEvent(this));
	}
	
	private void registerListeners() {
		Collection<ApplicationListener> values = getBeansOfType(ApplicationListener.class).values();
		for (ApplicationListener value : values) {
			applicationEventMulticaster.addApplicationListener(value);
		}
	}
	
	private void initApplicationEventMulticaster() {
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		applicationEventMulticaster = new DefaultApplicationEventMulticaster(beanFactory);
		beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
	}
	
	@Override
	public void publishEvent(ApplicationEvent event) {
		applicationEventMulticaster.multicastEvent(event);
	}
	
	@Override
	public void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(this::close));
	}
	
	@Override
	public void close() {
		//发布容器关闭事件
		publishEvent(new ContextClosedEvent(this));
		
		// 执行销毁单例bean的销毁方法
		getBeanFactory().destroySingletons();
	}
	
	private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanPostProcessor> postProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
		for (BeanPostProcessor beanPostProcessor : postProcessorMap.values()) {
			beanFactory.addBeanPostProcessor(beanPostProcessor);
		}
	}
	
	private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanFactoryPostProcessor> beanFppMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
		for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFppMap.values()) {
			beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		}
		
	}
	
	protected abstract ConfigurableListableBeanFactory getBeanFactory();
	
	protected abstract void refreshBeanFactory() throws BeansException;
	
	
	@Override
	public Object getBean(String beanName, Object... args) throws BeansException {
		return getBeanFactory().getBean(beanName, args);
	}
	
	@Override
	public Object getBean(String beanName) throws BeansException {
		return getBeanFactory().getBean(beanName);
	}
	
	@Override
	public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
		return getBeanFactory().getBean(beanName, requiredType);
	}
	
	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return getBeanFactory().getBeansOfType(type);
	}
	
	@Override
	public String[] getBeanDefinitionNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}
}
