package com.sty.spring.aop;

/**
 *  切点匹配过滤器
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public interface ClassFilter {
	
	/**
	 *  切入点是否应该应用于给定的接口或目标类
	 *
	 * @param clazz 目标类
	 * @return true,false
	 */
	boolean matches(Class<?> clazz);
}
