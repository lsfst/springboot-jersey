/**
 * springboot-jersey 版权声明
 * Copyright (c) 2019, EouTech All rights reserved
 *
 * @brief 文件说明
 * <p>
 * TODO 本文件功能作用详细说明
 * <p>
 * @history 修订说明
 * 20190601    lsf     初始版本
 */
package com.jersey.provider.filter;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;

/**
 * Restful服务是无状态的，但我们需要保证请求的安全性，所以需要对接口权限进行限制
 */

//@Provider
@Priority( Priorities.AUTHENTICATION)
public class AuthRequestFilter implements ContainerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(ContainerRequestFilter.class);

    @Context
    HttpServletRequest webRequest;

    @Context
    ServletConfig servletConfig;

    @Context
    ServletContext servletContext;

    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException {

        logger.info( "=====basic auth=====" );
        final Charset CHARACTER_SET = Charset.forName("utf-8");

        String authHeader = requestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Basic")) {
            String decoded = new String( Base64.decode(authHeader.substring(6).getBytes()), CHARACTER_SET);
//                  String decoded = Base64.decodeAsString(authHeader.substring(6));
            final String[] split = decoded.split(":");
            final String username = split[0];
            final String pwd = split[1];
            if (pwd.equals(pwd)) {//这里做了最大简化
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return new Principal() {
                            @Override
                            public String getName() {
                                return username;
                            }
                        };
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return true;
                    }

                    @Override
                    public boolean isSecure() {
                        return false;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return "BASIC";
                    }
                });
                return;
            }
        }

        requestContext.abortWith( Response.status(401).header( HttpHeaders.WWW_AUTHENTICATE, "Basic").build());

    }
}