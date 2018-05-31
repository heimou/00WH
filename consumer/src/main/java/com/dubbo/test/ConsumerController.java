package com.dubbo.test;

/**
 * @author liyujie
 * @Description:
 * @date: 2018/5/29
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
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
        RpcContext.getContext().setAttachment("param","1");
        System.out.println("开始调用："+sayHello.sayHello("开始调用!"));
            return "正常结束" ;
    }
}
