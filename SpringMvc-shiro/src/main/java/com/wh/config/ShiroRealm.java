package com.wh.config;

import com.wh.entity.UserDO;
import com.wh.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: ShiroRelam 用于验证用户的权限
 * @author:liyujie
 * @date:2018/6/15
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 为当前登录的用户授予角色和权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName=(String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //设置角色和权限 从数据库中读取  userDO 中可以获取到角色和权限去进行一次转换
        Set<String> roles=new HashSet<String>();
        Set<String> permissions=new HashSet<String>();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 验证当前登录的用户
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        获取用户名
        String userName = (String) token.getPrincipal();
        UserDO userDO = userService.getUserInfoByName(userName);

        System.out.println("用户信息:>>>>>>>>>>>>>>>>");
        System.out.println("userName:>>>>>>>>>>>" + userDO.getUsername());
        System.out.println("password:>>>>>>>>>>>" + userDO.getPassword());
        if (userDO == null) {
            return null;
        }

        // 发送请求到Shiro 中进行验证 和处理
        // 用户名，密码，角色
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userDO.getUsername(), userDO.getPassword(), userDO.getName()
        );

        return authenticationInfo;
    }
}
