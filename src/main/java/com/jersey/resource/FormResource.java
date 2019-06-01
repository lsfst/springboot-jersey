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


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Map;

/**
 * url参数提取
 * 请求header提取
 * form参数提取
 */
@Component
@Path( "form" )
public class FormResource {
    @GET
    @Path( "{id}" )
    public String get(@Context UriInfo ui) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        MultivaluedMap<String, String> pathParams = ui.getPathParameters();
        System.out.println( JSONObject.toJSONString( queryParams ) );
        System.out.println( JSONObject.toJSONString( pathParams ) );
        return "ok";
    }

    @GET
    public String get(@Context HttpHeaders hh) {
        MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
        Map<String, Cookie > pathParams = hh.getCookies();
        System.out.println( JSONObject.toJSONString( headerParams ) );
        System.out.println( JSONObject.toJSONString( pathParams ) );
        return "ok";
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public String post(@FormParam("name") String name) {
        // Store the message
        System.out.println(name );
        return "ok";
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public String post( MultivaluedMap<String, String> formParams) {
        // Store the message
        System.out.println( JSONObject.toJSONString( formParams ) );
        return "0k";
    }

}