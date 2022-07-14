package com.sty.sf.beans.factory;

import java.util.Map;

/**
 * 拓展接口
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     *  按照类型返回bean实例
     * @param type 类型
     * @param <T> 泛型
     * @return 返回bean实例
     * @throws BeansException 异常
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     *   返回注册表中所有的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
