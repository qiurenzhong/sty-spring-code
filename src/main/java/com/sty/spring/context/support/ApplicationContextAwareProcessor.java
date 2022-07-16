package com.sty.spring.context.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.config.BeanPostProcessor;
import com.sty.spring.context.ApplicationContext;
import com.sty.spring.context.ApplicationContextAware;

/**
 *  应用上下包装类
 * <p>
 *     由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到，
 *     所以需要在 refresh 操作时，把 ApplicationContext 写入到一个包装的 BeanPostProcessor 中去，
 *     再由 AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization 方法调用
 * </p>
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

	private final ApplicationContext applicationContext;

	public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof ApplicationContextAware) {
			((ApplicationContextAware) bean).setApplicationContext(applicationContext);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
