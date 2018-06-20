package com.test.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.dubbo.test.SayHello;


/**
 * @author liyujie
 * @Description: dubbo 生产者测试demo
 * @date: 2018/5/28
 */
@Service
public class SayHelloServiceImpl implements SayHello {

    @Override
    public String sayHello(String name) {
        System.out.println("dubbo RpcContext:"+ RpcContext.getContext().getAttachment("param"));


        return "生产者："+name;
    }
}
