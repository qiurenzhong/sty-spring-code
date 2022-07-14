package com.sty.sf.beans.utils;

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
}
