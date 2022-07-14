package com.sty.spring.context.support;

import com.sty.spring.beans.factory.support.DefaultListableBeanFactory;
import com.sty.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *  抽象Xml应用上下文
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
