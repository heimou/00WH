package com.wh;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Description: java8 时间测试
 * @author:liyujie
 * @date:2018/6/13
 */
public class Java8DateTest {


    public static void main(String[] args) throws Exception {
        LocalDateTime startTime=LocalDateTime.now();
        System.out.println("开始时间："+startTime);
        //获取java 8 时间
        try {
            Thread.currentThread().sleep(10);
        }catch (InterruptedException E){
        }

        Duration duration=Duration.between(startTime,LocalDateTime.now());
        System.out.println("休眠时间:"+duration);
        System.out.println( "LocalDate.now() "+LocalDate.now());
        System.out.println("LocalTime.now() "+LocalTime.now());
        System.out.println("LocalDateTime.now() "+LocalDateTime.now());
    }





}
