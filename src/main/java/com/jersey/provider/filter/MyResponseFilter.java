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
package com.jersey.provider.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @Provider提供给Jersey自动发现 //实际测试并没有效果，待继续验证，只能在JerseyConfig显式注册
 * @Component让该Filter交给Spring容器管理
 *
 */
@Provider
@Component
public class MyResponseFilter implements ContainerResponseFilter {

//    @Autowired
//    private ApplicationContext ctx;

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        System.out.println("===my response filter ===");
//        System.out.println(this);
//        System.out.println(ctx.getBeansOfType(MyResponseFilter.class));
    }
}