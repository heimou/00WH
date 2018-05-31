package com.wh.dubbo.demo.consumer;

import com.dubbo.test.SayHello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:${消费者启动类}
 * @author:liyujie
 * @date:2018/5/31
 */
public class ConsumerApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-demo-consumer.xml"});
        context.start();
        SayHello sayHello = (SayHello) context.getBean("SayHello");

        while (true) {

            try {
                Thread.sleep(1000);
                String hello = sayHello.sayHello("world");
                System.out.println(hello);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }


        }

    }
}
