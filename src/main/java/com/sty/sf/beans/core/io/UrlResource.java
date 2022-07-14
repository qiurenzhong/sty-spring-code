package com.sty.sf.beans.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL流操作
 *
 * @author tianma
 * @date 2022/6/28
 * @version 1.0.0
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection conn = this.url.openConnection();
        try {
            return conn.getInputStream();
        } catch (Exception e) {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
            throw new IOException(e);
        }
    }
}
