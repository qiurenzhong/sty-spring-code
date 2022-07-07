package com.sty.sf.beans.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *  资源加载接口
 *
 * @author tianma
 * @date 2022/6/28
 * @version 1.0.0
 */
public interface Resource {

    /**
     *  获取流
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

}
