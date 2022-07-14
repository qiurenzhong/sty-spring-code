package com.sty.sf.beans.context.support;

import com.sty.sf.beans.factory.BeansException;
import com.sty.sf.beans.factory.ConfigurableListableBeanFactory;
import com.sty.sf.beans.factory.support.DefaultListableBeanFactory;

/**
 *  获取bean工厂和加载资源
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
