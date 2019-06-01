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
package com.jersey.resource;

import com.jersey.service.ISomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Component
@Path( "resource" )
public class SpringbootResource {

    @Autowired
    private ISomeService someService;
    @Autowired
    private ApplicationContext ctx;


    @Path( "sayhi" )
    @GET
//    @StaticCompress
    public String sayHi( @QueryParam( "msg" ) String msg ) {
        this.someService.sayHi( msg );
        return "success";
    }

    @Path( "resource" )
    @GET
    public String resource1( @QueryParam( "msg" ) String msg ) {
        //验证SpringbootResource是否已经交给applicationContext管理
//        System.out.println( this );
//        System.out.println( ctx.getBeansOfType( SpringbootResource.class ) );
        this.someService.sayHi( msg );
        return "success";
    }
}