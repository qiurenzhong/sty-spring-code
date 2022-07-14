package com.sty.sf.beans.core.io;

/**
 *  资源加载接口
 *
 * @author tianma
 * @date 2022/6/28
 * @version 1.0.0
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";
    /**
     *  获取资源
     * @param location  单位
     * @return 返回结果
     */
    Resource getResource(String location);

}
