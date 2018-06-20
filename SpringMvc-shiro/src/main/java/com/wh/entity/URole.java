package com.wh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 角色信息
 * @author:liyujie
 * @date:2018/6/11
 */
@Data
public class URole implements Serializable {
    private static final long serialVersionUID = 563664190272906377L;

    private Long id;
    private String name;
    private String type;

}
