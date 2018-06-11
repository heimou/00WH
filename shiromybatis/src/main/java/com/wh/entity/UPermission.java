package com.wh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 存放用户跳转地址
 * @author:liyujie
 * @date:2018/6/11
 */
@Data
public class UPermission  implements Serializable {
    private static final long serialVersionUID = -4979417082462412257L;

    private Long id;
    private String url;
    private String name;

}
