package com.wh.service.impl;

import com.wh.dao.UserInfoDao;
import com.wh.entity.UserDO;
import com.wh.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author:liyujie
 * @date:2018/6/8
 */
@Service("ShiroService")
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserDO getUserinfobyId(Long id) {
        return userInfoDao.getuserByid(id);
    }




}
