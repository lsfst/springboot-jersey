/**
 * jerseystd 版权声明
 * Copyright (c) 2019, EouTech All rights reserved
 *
 * @brief 文件说明
 * <p>
 * TODO 本文件功能作用详细说明
 * <p>
 * @history 修订说明
 * 20190529    lsf     初始版本
 */
package com.jersey.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * 路由的正则过滤
 */
@Path("/greet/{name: [a-zA-Z][a-zA-Z_0-9]*}")
@Component
@Produces( MediaType.TEXT_PLAIN)
public class GreetMatchResource {
    @GET
    public String sayHello( @PathParam( "name" ) String name){
        return "Hello,"+name+"!";
    }
}