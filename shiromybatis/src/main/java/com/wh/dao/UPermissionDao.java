package com.wh.dao;

import com.wh.entity.UPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户权限Dao
 * @author:liyujie
 * @date:2018/6/11
 */
@Repository("UPermissionDao")
@Mapper
public interface UPermissionDao {
    /**
     * 根据用户Id 获取用户权限
     * @param id
     * @return
     */
    List<UPermission> findPermissionByUid(Long id);
}
