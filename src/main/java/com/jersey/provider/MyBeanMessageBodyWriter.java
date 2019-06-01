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
package com.jersey.provider;

import com.jersey.entity.MyBean;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 自定义MessageBodyWriter<T>，它可以将这个POJO序列化为XML
 */
@Produces( "application/xml" )
public class MyBeanMessageBodyWriter implements MessageBodyWriter< MyBean > {

    @Override
    public boolean isWriteable( Class< ? > type, Type genericType,
                                Annotation[] annotations, MediaType mediaType ) {
        return type == MyBean.class;
    }

    @Override
    public long getSize( MyBean myBean, Class< ? > type, Type genericType,
                         Annotation[] annotations, MediaType mediaType ) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return -1;
    }

    @Override
    public void writeTo( MyBean myBean,
                         Class< ? > type,
                         Type genericType,
                         Annotation[] annotations,
                         MediaType mediaType,
                         MultivaluedMap< String, Object > httpHeaders,
                         OutputStream entityStream )
            throws IOException, WebApplicationException {

        System.out.println( "====MyBeanMessageBodyWriter====" );

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( MyBean.class );

            // serialize the entity myBean to the entity output stream
            jaxbContext.createMarshaller().marshal( myBean, entityStream );
        } catch ( JAXBException jaxbException ) {
            throw new ProcessingException(
                    "Error serializing a MyBean to the output stream", jaxbException );
        }
    }
}