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
package com.jersey.resource;

import com.jersey.exception.CustomNotFoundException;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 构建response：
 * 请求头 Location →http://localhost:9090/opt/ecc
 * code 201 created
 * entity body ----MessageBodyWriter not found for media type=text/xml, type=class javax.ws.rs.client.Entity, genericType=class javax.ws.rs.client.Entity、
 * 暂时无法使用xml输出
 */
@Component
@Path( "xml" )
public class XmlResource {

    @POST
    @Produces( { MediaType.APPLICATION_JSON } )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response post( String content ) throws URISyntaxException {

        URI createdUri = new URI( "/opt/ecc" );
        if ( content.isEmpty() ) {
            throw new CustomNotFoundException( "content is empty" );
        }
//        return Response.created(createdUri).build();
        return Response.created( createdUri ).entity( Entity.text( content ) ).build();
    }
}