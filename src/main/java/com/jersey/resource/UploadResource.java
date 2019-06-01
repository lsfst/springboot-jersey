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
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.jersey.provider.ConfigConst.RESOURCE_PATH;

/**
 * 文件上传
 */
@Component
@Path( "upload" )
public class UploadResource {


    /**
     * 两种都可以用，建议使用第一种
     * @param fileInputStream 直接使用InputStream来提供上传文件的输入流
     * @param disposition 对multipart/form-data中一段content-disposition的封装，较之FormDataBodyPart提供了更高级的方法抽象
     * @param ctx
     * @return
     */
    @POST
    @Path("v1")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes( MediaType.MULTIPART_FORM_DATA)
    public String upload( @FormDataParam("file") InputStream fileInputStream,
                          @FormDataParam("file") FormDataContentDisposition disposition, @Context ServletContext ctx) {
        //获取文件名；
        System.out.println("getFileName : " + disposition.getFileName());
        //获取字段名称，即<input type="file" name="xxx"）中的xxx
        System.out.println("getName : " + disposition.getName());
        //获取该段content-disposition的内容长度
        System.out.println("getSize : " + disposition.getSize());
        //获取该段content-disposition的类型，比如form-data
        System.out.println("getType : " + disposition.getType());
        //获取本次请求的请求值，比如{name=file, filename=3.jpg}
        System.out.println("getParameters : " + disposition.getParameters());

        //可以选择设置文件上传临时目录，默认每次启动生成一个临时目录，windows下默认在C:/Users/Administrator/AppData/Local/Temp/+jetty(容器)-docbase.8698630128740945915.9090(端口)/，linux在/tmp下

//        File upload = new File(ctx.getRealPath(RESOURCE_PATH),
//                UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(disposition.getFileName()));

        File upload = new File(RESOURCE_PATH,
                UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(disposition.getFileName()));
        try {
            FileUtils.copyInputStreamToFile(fileInputStream, upload);
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return upload.getName();
    }


    /**
     * 使用较为底层的FormDataBodyPart
     * @param bp 对使用multipart/form-data编码请求实体的中的每一段实体内容的封装
     * @param ctx
     * @return
     */
    @POST
    @Path("v2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String upload2( @FormDataParam("file") FormDataBodyPart bp, @Context ServletContext ctx) {
        //获取某一段实体请求的名字；
//        System.out.println( bp.getName() );
        //获取某一段实体请求的值(该方法只能作用于text/plain内容)，如果用在文件上会报错
//        System.out.println( bp.getValue() );
        //获取某一段实体请求的封装对象，得到一个BodyPartEntity对象，可以调用getInputStream()方法获取实体内容等；
//        System.out.println( bp.getEntity() );
        //可以将请求转化为输入流（这也是一种获取上传文件内容的方法）；
//        InputStream is = bp.getValueAs( InputStream.class );
        //包装成FormDataContentDisposition对象返回；
//        FormDataContentDisposition disp = bp.getFormDataContentDisposition();

        File upload = new File(RESOURCE_PATH, UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(bp.getFormDataContentDisposition().getFileName()));
        try {
            FileUtils.copyInputStreamToFile(bp.getValueAs(InputStream.class), upload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return upload.getName();
    }
}