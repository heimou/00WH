package com.wh.service;

import com.wh.entity.UserDO;

/**
 * @Description: ShiroSerice interface
 * @author:liyujie
 * @date:2018/6/8
 */

public interface ShiroService {
    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    UserDO getUserinfobyId(Long id);

}
