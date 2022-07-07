package com.sty.sf.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于传递类中的属性信息
 *
 * @author tianma
 * @date 2022/6/25
 * @version 1.0.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();


    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
