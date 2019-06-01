/**
 * springboot-jersey 版权声明
 * Copyright (c) 2019, EouTech All rights reserved
 *
 * @brief 文件说明
 * <p>
 * TODO 本文件功能作用详细说明
 * <p>
 * @history 修订说明
 * 20190529    lsf     初始版本
 */
package com.jersey.config;

import com.jersey.provider.CompressionDynamicBinding;
import com.jersey.provider.MyBeanMessageBodyReader;
import com.jersey.provider.MyBeanMessageBodyWriter;
import com.jersey.provider.filter.AuthRequestFilter;
import com.jersey.provider.filter.MyResponseFilter;
import com.jersey.provider.interceptor.GZIPReaderInterceptor;
import com.jersey.provider.interceptor.GZIPWriterInterceptor;
import com.jersey.resource.*;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.ext.ContextResolver;
import java.util.HashMap;
import java.util.Map;


/**
 * springboot默认把Jersey的根路径映射在/*上，可以使用@ApplicationPath注解或spring.jersey.application-path=api配置
 *
 * Jersey’s support for scanning executable archives is rather limited. For example, it cannot scan for endpoints in a package found in a fully executable jar file
 * or in WEB-INF/classes when running an executable war file. To avoid this limitation, the packages method should not be used, and endpoints should be registered
 * individually by using the register method.
 * Springboot建议在使用ResourceConfig添加资源类的时候，不要使用ResourceConfig类的packages方法去自动扫描，而是手动添加
 * 原因是Jersey的packages比较局限的，比如在应用运行在war包中的时候，就不能扫描到其中的包。所以建议单独的为每一个资源类独立使用register方法注册
 *
 * 也可以使用@bean注册
 * @Bean
 * public ResourceConfig resourceConfig() {
 *     ResourceConfig config = new ResourceConfig();
 *     config.register(SpringbootResource.class);
 *     return config;
 * }
 *
 * JerseyProperties.class可以找到对应配置
 */
@Component
//@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        //json序列化  支持jsonp
        property( MarshallerProperties.JSON_NAMESPACE_SEPARATOR, "." );
        register( MoxyJsonFeature.class )
                .property( JsonGenerator.PRETTY_PRINTING, true )
                .register( JsonProcessingFeature.class );

        //上传下载注册：MultiPartFeature是Jersey中针对Multipart的一种特征（Feature，Feature是JAX-RS中的一种规范，可以视为一种特殊的meta-provider，
        // 通常把多个相同类型的Provider封装到一起，比如针对多种encoding的Provider打包注册）
        register( MultiPartFeature.class );

        //资源注册
        register( SpringbootResource.class )
                .register( XmlResource.class )
                .register( BeanResource.class )
                .register( AnnotatedResource.class )
                .register( WorkersResource.class )
                .register( DepartmentResource.class )
                .register( FormResource.class )
                .register( GreetMatchResource.class )
                .register( HelloResource.class )
                .register( UploadResource.class )
                .register( DownloadResource.class )
                .register( ImageResource.class );

        // 自定义request/response过滤器/拦截器
        register( MyBeanMessageBodyWriter.class )
                .register( MyBeanMessageBodyReader.class )
                .register( AuthRequestFilter.class );

        //NameBind动态绑定
        register( CompressionDynamicBinding.class );
        //register( GZIPWriterInterceptor.class );
        //NameBind静态绑定
        register( GZIPReaderInterceptor.class );

    }
}