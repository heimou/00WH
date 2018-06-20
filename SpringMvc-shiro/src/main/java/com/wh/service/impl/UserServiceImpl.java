package com.wh.service.impl;

import com.wh.dao.UserInfoDao;
import com.wh.entity.UserDO;
import com.wh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户信息实现类
 * @author:liyujie
 * @date:2018/6/15
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserDO getUserInfoById(Long id) {
        return userInfoDao.getuserByid(id);
    }

    @Override
    public UserDO getUserInfoByName(String userName) {
        return userInfoDao.getuserByName(userName);
    }
}
