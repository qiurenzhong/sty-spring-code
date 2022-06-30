package com.sty.sf.beans;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/25
 * @version 1.0.0
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


}
