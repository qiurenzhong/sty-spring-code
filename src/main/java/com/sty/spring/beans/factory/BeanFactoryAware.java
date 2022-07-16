package com.sty.spring.beans.factory;

import com.sty.spring.beans.BeansException;

/**
 *  容器感知类
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public interface BeanFactoryAware extends Aware {

	/**
	 *  设置Bean工厂
	 *  <p>
	 *      实现此接口，既能感知到所属的BeanFactory
	 *  </p>
	 * @param beanFactory
	 * @throws BeansException
	 */
	void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
