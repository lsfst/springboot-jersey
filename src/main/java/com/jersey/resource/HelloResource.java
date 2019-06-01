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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @PATH 对应的是我们要配置的restful api的子路劲，比如前面我们配置的是/api/*,则用户访问该API的路径就是https//:ip:port/api/hello
 * @GET @POST 对应的是用户请求资源用的HTTP方法
 * @Produces 表示返回的数据类型，如MediaType.TEXT_PLAIN对应返回文本类型
 *
 * 支持注入子类资源（无需在ResourceConfig显示注册）:配合spring @Autowired自动注入SubHelloResource
 *
 */
@Path("hello")
@Component
public class HelloResource {

    @Autowired
    SubHelloResource subHelloResource;

    @GET
    @Produces( MediaType.TEXT_PLAIN)
    public String sayHello(){
        return "Hello,I am text!";
    }


    @POST
    @Produces( MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello,I am xml!" + "</hello>";
    }

    @GET
    @Produces( MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello,I am html!" + "</h1></body>" + "</html> ";
    }


    @Path("sub")
    public SubHelloResource getSubHello() {
        return subHelloResource;
    }


}