package com.wh.service;

import com.wh.entity.UserDO;

/**
 * @Description:
 * @author:liyujie
 * @date:2018/6/15
 */
public interface UserService {
    /**
     * 通过id 获取用户信息
     * @param id
     * @return
     */
    UserDO getUserInfoById(Long id);

    /**
     * 通过userName 获取用户信息
     * @param userName
     * @return
     */
    UserDO getUserInfoByName(String userName);
}
