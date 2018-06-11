package com.wh.shiroConfig;

import com.wh.dao.UPermissionDao;
import com.wh.dao.URoleDao;
import com.wh.dao.UserInfoDao;
import com.wh.entity.UPermission;
import com.wh.entity.URole;
import com.wh.entity.UserDO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: shiroRealm  验证用户信息
 * @date:2018/6/11
 */

public class ShiroRealm extends AuthorizingRealm {

    private final static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private URoleDao uRoleDao;

    @Autowired
    private UPermissionDao uPermissionDao;


    /**
     * 权限认证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info(">>>>>>>>>>>>>>>>执行Shiro 权限认证<<<<<<<<<<<<<<");
        //获取当前用户名
        UserDO userDO = (UserDO) principals.getPrimaryPrincipal();
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
//        String loginName = (String) super.getAvailablePrincipal(principalCollection);
//        //到数据库查是否有此对象
//        User user = null;// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        user = userMapper.findByName(loginName);
        if (userDO != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.addRoles(userDO.getRoleStrlist());
            //用户的权限集合
            info.addStringPermissions(userDO.getPerminsStrlist());

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 用户信息存在在token  中
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        logger.info("验证当前Subject时获取到的token:"+token.toString());
        //数据库查出是否有当前用户
        UserDO userDO=userInfoDao.getuserByName(token.getUsername());
        if(userDO!=null){
            //若存在 ，将此用户存放到登录认证info 中。无需自己做密码比对。shiro 会为我们进行密码对比校验
            List<URole> rlist = uRoleDao.findRoleByUid(userDO.getId());//获取用户角色
            List<UPermission> plist = uPermissionDao.findPermissionByUid(userDO.getId());//获取用户权限
            List<String> roleStrlist=new ArrayList<String>();////用户的角色集合
            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
            for (URole role : rlist) {
                roleStrlist.add(role.getName());
            }
            for (UPermission uPermission : plist) {
                perminsStrlist.add(uPermission.getName());
            }
            userDO.setRoleStrlist(roleStrlist);
            userDO.setPerminsStrlist(perminsStrlist);
        return new SimpleAuthenticationInfo(userDO,userDO.getPassword()
        ,userDO.getName());
        }
        return null;
    }
}
