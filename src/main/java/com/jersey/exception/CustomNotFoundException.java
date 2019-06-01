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
package com.jersey.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * 自定义异常处理
 */
public class CustomNotFoundException extends WebApplicationException {

    /**
     * Create a HTTP 404 (Not Found) exception.
     */
    public CustomNotFoundException() {
        super( Response.Status.NOT_FOUND);
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public CustomNotFoundException(String message) {
        super( Response.status( Response.Status.NOT_FOUND).
                entity(message).type("text/plain").build());
    }

}