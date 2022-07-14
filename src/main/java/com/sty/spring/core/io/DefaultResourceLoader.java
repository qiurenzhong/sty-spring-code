package com.sty.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *  默认加载资源实现
 *
 * @author tianma
 * @date 2022/6/28
 * @version 1.0.0
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must be not null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            location = location.replaceAll(CLASSPATH_URL_PREFIX, "/");
            return new ClassPathResource(location);
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
