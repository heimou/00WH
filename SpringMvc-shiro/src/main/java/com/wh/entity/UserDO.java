package com.wh.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


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
    private Long id;
    /**
     * User.name
     */
    private String name;
    /**
     * User.password
     */
    private String password;
    /**
     *  盐 用于加密使用 也可配置在配置文件中
     */
    private String salt;
    /**
     * 登录用户名
     */
    private  String username;
    /**
     * 角色信息
     */
    private List<String> roleStrlist;
    /**
     * 权限信息
     */
    private List<String> perminsStrlist;
}
