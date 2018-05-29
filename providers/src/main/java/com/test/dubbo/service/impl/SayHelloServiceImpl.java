package com.test.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.test.SayHello;


/**
 * @author liyujie
 * @Description:
 * @date: 2018/5/28
 */
@Service
public class SayHelloServiceImpl implements SayHello {

    @Override
    public String sayHello(String name) {
        return "生产者："+name;
    }
}
