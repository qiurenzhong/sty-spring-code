package com.sty.spring.context.event;

import com.sty.spring.beans.factory.BeanFactory;
import com.sty.spring.context.ApplicationEvent;
import com.sty.spring.context.ApplicationListener;

import java.util.Collection;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public class DefaultApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public DefaultApplicationEventMulticaster(BeanFactory beanFactory) {
		setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
	    Collection<ApplicationListener> applicationListeners = getApplicationListeners(event);
	    for (final ApplicationListener applicationListener : applicationListeners) {
		   applicationListener.onApplicationEvent(event);
	    }
    }
}
