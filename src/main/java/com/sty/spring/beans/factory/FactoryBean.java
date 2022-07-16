package com.sty.spring.beans.factory;

/**
 * FactoryBean 接口
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public interface FactoryBean<T> {

	/**
	 *  获取对象
	 *
	 * @return 对象
	 * @throws Exception 异常
	 */
	T getObject() throws Exception;

	/**
	 *  对象类型
	 *
	 * @return 类型
	 */
	Class<?> getObjectType();

	/**
	 *  是否是单例对象
	 * @return true,false
	 */
	boolean isSingleton();

}
