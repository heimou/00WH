package com.dubbo.test;

/**
 * @author liyujie
 * @Description:
 * @date: 2018/5/29
 */

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbotest")
public class ConsumerController {
    @Reference
    SayHello sayHello;

    @GetMapping("/test")
    public String getDubboTest(){
       return sayHello.sayHello("开始调用!");
    }
}
