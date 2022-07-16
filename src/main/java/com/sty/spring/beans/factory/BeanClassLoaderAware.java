package com.sty.spring.beans.factory;

/**
 *  Bean的类加载感知
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public interface BeanClassLoaderAware extends Aware{

	/**
	 *  设置类加载
	 *  <p>
	 *      实现此接口，既能感知到所属的ClassLoader
	 *  </p>
	 * @param beanClassLoader
	 */
	void  setBeanClassLoader(ClassLoader beanClassLoader);
}
