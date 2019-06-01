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

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static com.jersey.provider.ConfigConst.RESOURCE_PATH;

@Component
@Path( "download" )
public class DownloadResource {

    /**
     * 传统的做法 ：实测有bug 输出内容 204 no content
     * @param fileName
     * @param response
     * @param ctx
     * @throws IOException
     */
    @GET
    @Path("/v1/{name}")
    public void download( @PathParam("name") String fileName, @Context HttpServletResponse response,
                         @Context ServletContext ctx) throws IOException {
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        response.setHeader("Cache-Control", "no-cache");
        File f = new File(RESOURCE_PATH, fileName);
        FileUtils.copyFile(f, response.getOutputStream());
    }

    /**
     * javax.ws.rs.core.Response类，这个类可以直接被JAX-RS作为一个Response输出 链式调用
     * 建议使用v2
     * @param fileName
     * @param ctx
     * @return
     * @throws IOException
     */
    @GET
    @Path("/v2/{name}")
    public Response download2(@PathParam("name") String fileName, @Context ServletContext ctx) throws IOException {
        File f = new File(RESOURCE_PATH, fileName);
        if (!f.exists()) {
            return Response.status( Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(f).header("Content-disposition", "attachment;filename=" + fileName)
                    .header("Cache-Control", "no-cache").build();
        }
    }


    /**
     * 对于某些没有源文件，直接输出流的内容，比如在内存中构造一个图片提供下载，或者构造一个Excel下载或者构造一个PDF文件提供下载等，就不能直接把一个原始File对象作为ok方法的参数，这个时候就需要使用到JAX-RS提供的StreamingOutput接口
     * 传入了一个匿名内部类，实现了StreamingOutput接口，在方法write中，直接把byte内容输出到了output参数中，这个output即为response的输出流
     * @param imageName
     * @param ctx
     * @return
     * @throws IOException
     */
    @GET
    @Path("/v3/{name}")
    public Response showImg2(@PathParam("name") String imageName, @Context ServletContext ctx) throws IOException {
        final File f = new File(RESOURCE_PATH, imageName);
        if (!f.exists()) {
            return Response.status( Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(new StreamingOutput() {
                @Override
                public void write( OutputStream output) throws IOException, WebApplicationException {
                    output.write(FileUtils.readFileToByteArray(f));
                }
            }).header("Content-disposition", "attachment;filename=" + imageName)
                    .header("Cache-Control", "no-cache").build();
        }
    }

}