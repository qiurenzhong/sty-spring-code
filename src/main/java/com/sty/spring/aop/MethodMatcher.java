package com.sty.spring.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public interface MethodMatcher {
	
	/**
	 *  匹配
	 * @param method 方法
	 * @param targetClass 目标类
	 * @return true,false
	 */
	boolean matches(Method method,Class<?> targetClass);
}
