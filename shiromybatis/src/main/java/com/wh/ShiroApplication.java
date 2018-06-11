package com.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @author:liyujie
 * @date:2018/6/11
 */
@SpringBootApplication
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
        System.out.println("启动完毕!");
    }
}
