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

import com.jersey.provider.Compress;
import com.jersey.provider.ProviderPriority;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * 非gzip会出错
 * 优先级设置
 */
@Compress
@Component
@Priority( ProviderPriority.ZIP_READER )
public class GZIPReaderInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom( ReaderInterceptorContext context )
            throws IOException, WebApplicationException {

        System.out.println( "====GZIPReaderInterceptor====" );

        final InputStream originalInputStream = context.getInputStream();
        context.setInputStream( new GZIPInputStream( originalInputStream ) );
        return context.proceed();
    }
}