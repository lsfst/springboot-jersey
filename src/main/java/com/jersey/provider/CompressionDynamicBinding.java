/**
 * springboot-jersey 版权声明
 * Copyright (c) 2019, EouTech All rights reserved
 *
 * @brief 文件说明
 * <p>
 * TODO 本文件功能作用详细说明
 * <p>
 * @history 修订说明
 * 20190530    lsf     初始版本
 */
package com.jersey.provider;

import com.jersey.provider.interceptor.GZIPWriterInterceptor;
import com.jersey.resource.BeanResource;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 动态绑定：可以实现正则匹配
 * 在方法级别进行匹配
 */
@Provider
public class CompressionDynamicBinding implements DynamicFeature {

    private AtomicInteger count = new AtomicInteger( 0 );

    @Override
    public void configure( ResourceInfo resourceInfo, FeatureContext context ) {

        System.out.println( "====CompressionDynamicBinding===="+ count.incrementAndGet() );

        if ( BeanResource.class.equals( resourceInfo.getResourceClass() )
                && resourceInfo.getResourceMethod()
                .getName().contains( "getMyBean" ) ) {
            context.register( GZIPWriterInterceptor.class );
        }

    }
}