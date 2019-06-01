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

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.lang.annotation.Annotation;

/**
 * @brief TODO 类功能作用及实现逻辑说明
 * @see
 */
@Component
@Path( "annotate" )
public class AnnotatedResource {

    @GET
    public Response get() {

        Annotation annotation = AnnotatedResource.class
                .getAnnotation( Path.class );
        return Response.ok()
                .entity( "Entity", new Annotation[] { annotation } ).build();
    }
}