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
package com.jersey.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 使用JAXB注释本身可以在一定程度上控制输出JSON格式。具体来说，只需使用JAXB注释就可以很容易地直接重命名和删除项
 *  @XmlElement(name = "name") 控制序列化输出的字段名
 *
 */
@XmlRootElement
public class MyBean {

    @XmlElement( name = "name" )
    public String anyString;

    @XmlElement
    public int anyNumber;

    public MyBean( String anyString, int anyNumber ) {
        this.anyString = anyString;
        this.anyNumber = anyNumber;
    }

    // empty constructor needed for deserialization by JAXB
    public MyBean() {}

    @Override
    public String toString() {
        return "MyBean{" +
                "anyString='" + anyString + '\'' +
                ", anyNumber=" + anyNumber +
                '}';
    }
}