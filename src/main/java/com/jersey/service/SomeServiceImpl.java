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
package com.jersey.service;

import org.springframework.stereotype.Service;

/**
 * @brief
 *    TODO 类功能作用及实现逻辑说明
 *
 * @see
 *
 */
@Service
public class SomeServiceImpl implements ISomeService{
    @Override
    public void sayHi(String msg) {
        System.out.println(msg);
    }
}