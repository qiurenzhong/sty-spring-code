package com.sty.spring.beans.factory;

/**
 *  bean的感知类接口
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public interface BeanNameAware extends Aware{

	/**
	 *  设置bean感知
	 *  <p>
	 *      实现此接口，既能感知到所属的BeanName
	 *  </p>
	 * @param beanName bean名称
	 */
	void setBeanName(String beanName);
}
