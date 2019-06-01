/**
 * springboot-jersey 版权声明
 * Copyright (c) 2019, EouTech All rights reserved
 *
 * @brief 文件说明
 * <p>
 * TODO 本文件功能作用详细说明
 * <p>
 * @history 修订说明
 * 20190529    lsf     初始版本
 */
package com.jersey.provider.interceptor;

import com.jersey.provider.ProviderPriority;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * write gzip
 */
@Priority( ProviderPriority.ZIP_READER )
public class GZIPWriterInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo( WriterInterceptorContext context )
            throws IOException, WebApplicationException {

        System.out.println( "=====GZIPWriterInterceptor====" );

        final OutputStream outputStream = context.getOutputStream();
        context.setOutputStream( new GZIPOutputStream( outputStream ) );
        context.proceed();
    }
}