package com.sty.spring.utils;

import com.sty.spring.context.ApplicationListener;

/**
 *  Class工具
 *
 * @author tianma
 * @date 2022/6/28
 * @version 1.0.0
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {

            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {

        }

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }

        return cl;
    }
	
	public static boolean isCglibProxyClass(Class<?> clazz) {
		return (clazz != null && isCglibProxyClassName(clazz.getName()));
	}
	
	public static boolean isCglibProxyClassName(String clazzName) {
		return (clazzName != null && clazzName.contains("$$"));
	}
}
