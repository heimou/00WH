package com.wh.dao.twodata;

import com.wh.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: UserInfoDao
 * @author:liyujie
 * @date:2018/6/8
 */
@Repository("UserInfotwoDao")
@Mapper
public interface UserInfotwoDao {
    /**
     *  获取用户信息
     * @param id
     * @return
     */
    UserDO getuserByid(Long id);
}
