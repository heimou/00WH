package com.wh.dao;

import com.wh.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: UserInfoDao
 * @author:liyujie
 * @date:2018/6/8
 */
@Repository("UserInfoDao")
@Mapper
public interface UserInfoDao {
    /**
     *  获取用户信息
     * @param id
     * @return
     */
    UserDO getuserByid(Long id);

    /**
     * 通过用户名来获取用户信息
     * @param name
     * @return
     */
    UserDO getuserByName(String name);
}
