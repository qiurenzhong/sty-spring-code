package com.sty.spring.context.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.ConfigurableListableBeanFactory;
import com.sty.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 *  可刷新的应用上下文
 *
 * @author tianma
 * @date 2022/6/30
 * @version 1.0.0
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);


    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
}
