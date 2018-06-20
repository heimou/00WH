package com.wh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 角色权限
 * @author:liyujie
 * @date:2018/6/11
 */
@Data
public class URolePermission  implements Serializable{
    private static final long serialVersionUID = -2492499277670416973L;
    /**
     * 角色Id
     */
    private Long rid;
    /**
     * 权限ID
     */
    private Long pid;
}
