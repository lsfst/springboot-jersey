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
package com.jersey.provider;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @NameBinding注解，代表这是JAX-RS中的一个名称绑定标记
 * 将 过滤器/拦截器与方法进行静态绑定，只对绑定方法作用
 */
@NameBinding
@Retention( RetentionPolicy.RUNTIME)
public @interface Compress {
}