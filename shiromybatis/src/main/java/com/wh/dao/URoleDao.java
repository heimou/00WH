package com.wh.dao;

import com.wh.entity.URole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @author:liyujie
 * @date:2018/6/11
 */
@Repository("URoleDao")
@Mapper
public interface URoleDao {
    /**
     * 根据用户ID获取该用户所在组的角色权限
     * @param obj
     */
    public List<URole> findRoleByUid(Long	 obj);
}
