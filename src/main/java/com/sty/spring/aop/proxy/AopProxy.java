package com.sty.spring.aop.proxy;

/**
 *  AOP 代理的抽象
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public interface AopProxy {
	
	/**
	 *  获取代理
	 * @return 代理对象
	 */
	Object getProxy();
}
