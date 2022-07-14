package com.sty.sf.beans.context.support;

import com.sty.sf.beans.factory.BeansException;

/**
 * xml文件应用上下文
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     *  从xml文件中加载BeanDefinition,并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     *  从xml文件中加载BeanDefinition,并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
