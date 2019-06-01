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
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 子资源注入
 */
@Component
public class SubHelloResource {
    @GET
    @Produces( MediaType.TEXT_PLAIN)
    public String sayHello(){
        return "SubHello,I am text!";
    }


    @POST
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> SubHello,I am xml!" + "</hello>";
    }
}