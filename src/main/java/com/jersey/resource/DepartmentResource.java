/**
 * jerseystd 版权声明
 * Copyright (c) 2019, EouTech All rights reserved
 *
 * @brief 文件说明
 * <p>
 * TODO 本文件功能作用详细说明
 * <p>
 * @history 修订说明
 * 20190529    lsf     初始版本
 */
package com.jersey.resource;

import com.jersey.entity.Department;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * json/xml输出
 */
@Path("dept")
@Component
public class DepartmentResource {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List< Department > list() {
        List<Department> dept = new ArrayList< Department >();
        dept.add(new Department(1L, "dept1"));
        dept.add(new Department(2L, "dept2"));
        return dept;
    }
}