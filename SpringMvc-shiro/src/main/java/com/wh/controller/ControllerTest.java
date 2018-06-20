package com.wh.controller;

import com.wh.entity.UserDO;
import com.wh.service.UserService;
import com.wh.utils.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: test Controller
 * @author:liyujie
 * @date:2018/6/15
 */
@RestController
public class ControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);
    @Autowired
    private UserService service;

    /**
     * 拔测
     *
     * @return
     */
    @GetMapping("/test")
    public String getHello() {
        return "Heart is break!!!";
    }


    @GetMapping("/getUserByid")
    public void getUserInfo() {
        UserDO userDO = service.getUserInfoById(Long.valueOf(1));
        logger.info("用户信息: ------> 用户名:" + userDO.getUsername());
        logger.info("用户信息: ------> 密码::" + userDO.getPassword());
    }

    /**
     * 登录信息
     *
     * @param userDO
     * @return
     */
    @PostMapping("/login")
    public String getLogin( UserDO userDO, HttpServletRequest request) {
        logger.info("用户信息:>>>>>>>>>>>>");
        logger.info("userName:>>>>>>>>>>>>" + userDO.getUsername());
        logger.info("passWord:>>>>>>>>>>>>" + userDO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        //  UsernamePasswordToken token = new UsernamePasswordToken(userDO.getUsername(), userDO.getPassword());
        //MD5 加密
        String salt = "shiro";
        UsernamePasswordToken token = new UsernamePasswordToken(userDO.getUsername(), CryptographyUtil.md5(userDO.getPassword(), salt));

        try {
            subject.login(token);
            //这里再去写自己的业务逻辑
            return "redirect:/success.jsp";
        } catch (Exception e) {
            // 这边异常可以考虑在封装下  不同的异常可以处理不同的事情
            // ObjException instanceof Exception
            e.printStackTrace();
            request.setAttribute("user", userDO);
            request.setAttribute("errorMsg", "用户 或密码错误！");
            return "index.jsp";
        }
    }
}
