package com.sty.sf.beans.context.support;

import com.sty.sf.beans.factory.support.DefaultListableBeanFactory;
import com.sty.sf.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
