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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ConfigConst {
    public static String RESOURCE_PATH ;

    @Value( "${upload.temp.dir}" )
    private void setResourcePath(String path){
        this.RESOURCE_PATH = path;
    }
}