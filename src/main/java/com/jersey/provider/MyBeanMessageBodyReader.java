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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 自定义MyBeanMessageBodyReader<T>，它可以将这个POJO反序列化
 * 只对MyBean作用
 */
public class MyBeanMessageBodyReader
        implements MessageBodyReader< MyBean > {

    @Override
    public boolean isReadable( Class< ? > type, Type genericType,
                               Annotation[] annotations, MediaType mediaType ) {
        return type == MyBean.class;
    }

    @Override
    public MyBean readFrom( Class< MyBean > type,
                            Type genericType,
                            Annotation[] annotations, MediaType mediaType,
                            MultivaluedMap< String, String > httpHeaders,
                            InputStream entityStream )
            throws IOException, WebApplicationException {

//        System.out.println( "====MyBeanMessageBodyReader====" );

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( MyBean.class );
            MyBean myBean = ( MyBean ) jaxbContext.createUnmarshaller()
                    .unmarshal( entityStream );
            return myBean;
        } catch ( JAXBException jaxbException ) {
            throw new ProcessingException( "Error deserializing a MyBean.",
                    jaxbException );
        }
    }
}