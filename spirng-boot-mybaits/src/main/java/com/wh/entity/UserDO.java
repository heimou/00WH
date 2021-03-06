package com.wh.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @Description: 用户基本信息
 * @author:liyujie
 * @date:2018/6/8
 */
@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = -5393716255575139122L;
    /**
     * table 主键id  可以在java 这边用全局序列来处理
     */
    private int uid;
    /**
     * User.name
     */
    private String name;
    /**
     * User.password
     */
    private String password;
    /**
     * 加密后的
     */
    private String salt;
    /**
     * 登录用户名
     */
    private  String username;
}
