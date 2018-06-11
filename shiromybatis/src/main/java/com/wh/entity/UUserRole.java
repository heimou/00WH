package com.wh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author:liyujie
 * @date:2018/6/11
 */
@Data
public class UUserRole implements Serializable {
    private static final long serialVersionUID = -3780032681533906878L;
    /**
     * /用户Id
     */
    private Long uid;
    /**
     * 角色id
     */
    private Long rid;
}
