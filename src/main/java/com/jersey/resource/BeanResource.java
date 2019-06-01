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
import com.jersey.provider.Compress;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("bean")
public class BeanResource {
    @GET
    @Produces("application/xml")
    public MyBean getMyBean() {
        return new MyBean("Hello World!", 42);
    }

    @Compress
    @POST
    @Consumes("application/xml")
    public String postMyBean(MyBean myBean) {
        return myBean.anyString;
    }

}