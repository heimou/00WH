package com.wh.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.wh.entity.UserDO;
import com.wh.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: shiroController
 * @author:liyujie
 * @date:2018/6/8
 */
@RestController
public class ShiroController {

    private final static Logger logger= LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private ShiroService shiroService;


    @GetMapping("/test")
    public  String getHeart(){return "get Heart !!!";}
    /**
     * 获取用户信息
     * @return
     */
        @GetMapping("/getUserInfo")
    public String getUserInfoByid() {
        UserDO userDO = shiroService.getUserinfobyId(Long.valueOf(1));
        System.out.println("UserDo is name:" + userDO.getName());
        System.out.println("UserDo is password:" + userDO.getPassword());
        System.out.println("======================");
        return "Hello World!";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("this is frame");
        return "common/frame";
    }

    @RequestMapping("/index")
    public String list(Model model) {
        System.out.println("this is index");
        return "index";
    }

    /**
     * 做一个数据验证
     * @param user
     * @return
     */
    @PostMapping("/validata")
    public String index(@RequestBody  UserDO user) {
        if (user.getName() != null && user.getPassword() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getName() + "]进行登录验证..验证开始");
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + user.getName() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    System.out.println("用户[" + user.getName() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    return "redirect:/";
                } else {
                    token.clear();
                    System.out.println("用户[" + user.getName() + "]登录认证失败,重新登陆");
                    return "redirect:/login";
                }
            } catch ( UnknownAccountException uae ) {
                logger.info("对用户[" + user.getName() + "]进行登录验证..验证失败-username wasn't in the system");
            } catch ( IncorrectCredentialsException ice ) {
                logger.info("对用户[" + user.getName() + "]进行登录验证..验证失败-password didn't match");
            } catch ( LockedAccountException lae ) {
                logger.info("对用户[" + user.getName() + "]进行登录验证..验证失败-account is locked in the system");
            } catch ( AuthenticationException ae ) {
                logger.error(ae.getMessage());
            }
        }
        return "login";
    }


    /**
     * ajax登录请求接口方式登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/ajaxLogin",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitLogin(String username, String password, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");

        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

}
