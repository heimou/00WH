package com.wh.service.impl;

import com.wh.dao.onedata.UserInfoDao;
import com.wh.dao.twodata.UserInfotwoDao;
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

    @Autowired
    private UserInfotwoDao userInfotwoDao;

    @Override
    public UserDO getUserinfobyId(Long id) {
        return userInfoDao.getuserByid(id);
    }

    @Override
    public UserDO getUserinfoByid2(Long id) {
        return userInfotwoDao.getuserByid(id);
    }


}
