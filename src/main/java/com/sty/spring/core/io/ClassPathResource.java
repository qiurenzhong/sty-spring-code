package com.sty.spring.core.io;

import cn.hutool.core.lang.Assert;
import com.sty.spring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPath 流文件操作
 *
 * @author tianma
 * @date 2022/6/28
 * @version 1.0.0
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "path must not be null");
        this.path = path;
        this.classLoader = (classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader);

    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getClass().getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because is does not exist");
        }
        return is;
    }
}
