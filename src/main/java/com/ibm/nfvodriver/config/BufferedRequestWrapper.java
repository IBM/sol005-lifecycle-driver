package com.ibm.nfvodriver.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class BufferedRequestWrapper extends HttpServletRequestWrapper {

    private final String requestBody;

    BufferedRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = IOUtils.toString(request.getReader());
    }

    @Override public ServletInputStream getInputStream() {
        return new ServletInputStream() {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());

            @Override public int read() {
                return byteArrayInputStream.read();
            }

            @Override public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override public boolean isReady() {
                return true;
            }

            @Override public void setReadListener(final ReadListener listener) {
                throw new UnsupportedOperationException("Not implemented");
            }
        };
    }

    @Override public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}