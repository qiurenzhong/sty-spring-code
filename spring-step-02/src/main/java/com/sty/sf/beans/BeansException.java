package com.sty.sf.beans;

/**
 * 异常
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message,Throwable e) {
        super(message,e);
    }
}
