package com.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: ActiveMq  启动类！
 * @author:liyujie
 * @date:2018/6/6
 */
@SpringBootApplication
public class ActiveMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class,args);
        System.out.println("启动完毕!");
    }
}
