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

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * 图片资源下载+异常处理
 */
@Component
@Path("/images")
public class ImageResource {
    private static String IMAGE_PATH = "/opt/jersey/data";

    @GET
    @Path("{image}")
    @Produces("image/*")
    public Response getImage( @PathParam("image") String image) {

        File f = new File(IMAGE_PATH+image);

        if (!f.exists()) {
            throw new WebApplicationException(  Response.status( Response.Status.NOT_FOUND).
                    entity("pic not exist").type("text/plain").build());
        }

        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }

}