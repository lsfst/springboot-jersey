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

import com.jersey.entity.MyBean;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * MessageBodyWorkers
 * 使用@Context注入。接口根据JAX-RS规范中定义的规则声明了选择最合适的MessageBodyReader<T>和MessageBodyWriter<T>的方法，这些方法用于编写和读取实体，确保正确和及时地调用拦截器和其他有用的方法。
 * <p>
 * 这个功能与MyBeanMessageBodyWriter重叠
 */
@Component
@Path( "workers" )
public class WorkersResource {
    @Context
    private MessageBodyWorkers workers;

    @GET
    @Produces( "application/xml" )
    public String getMyBeanAsString() {

        final MyBean myBean = new MyBean( "Hello World!", 42 );

        // buffer into which myBean will be serialized
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // get most appropriate MBW
        final MessageBodyWriter< MyBean > messageBodyWriter =
                workers.getMessageBodyWriter( MyBean.class, MyBean.class,
                        new Annotation[] {}, MediaType.APPLICATION_XML_TYPE );

        try {
            // use the MBW to serialize myBean into baos
            messageBodyWriter.writeTo( myBean,
                    MyBean.class, MyBean.class, new Annotation[] {},
                    MediaType.APPLICATION_XML_TYPE, new MultivaluedHashMap< String, Object >(),
                    baos );
        } catch ( IOException e ) {
            throw new RuntimeException(
                    "Error while serializing MyBean.", e );
        }

        final String stringXmlOutput = baos.toString();
        // stringXmlOutput now contains XML representation:
        // "<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        // <myBean><anyString>Hello World!</anyString>
        // <anyNumber>42</anyNumber></myBean>"

        return stringXmlOutput;
    }
}