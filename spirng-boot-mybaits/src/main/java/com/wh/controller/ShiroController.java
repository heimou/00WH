package com.wh.controller;

import com.wh.entity.UserDO;
import com.wh.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: shiroController
 * @author:liyujie
 * @date:2018/6/8
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private ShiroService shiroService;


    @GetMapping("/test")
    public  String getHeart(){return "get Heart !!!";}
    /**
     * 获取用户信息
     * 多数据源一起获取
     * @return
     */
    @GetMapping("/getUserInfo")
    public String getUserInfoByid() {
        UserDO userDO = shiroService.getUserinfobyId(Long.valueOf(1));
        System.out.println("UserDo is name:" + userDO.getName());
        System.out.println("UserDo is password:" + userDO.getPassword());
        System.out.println("======================");
        UserDO userDO1 = shiroService.getUserinfoByid2(Long.valueOf(1));
        System.out.println("UserDo is name:" + userDO1.getName());
        System.out.println("UserDo is password:" + userDO1.getPassword());
        return "Hello World!";
    }


}
