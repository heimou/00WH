package com.wh.activeMq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Description: Spring boot ActiveMq cousumer
 * @author:liyujie
 * @date:2018/6/5
 */
@Component
public class ActvieMqConsumer2 {

    /**
     * @param text
     * @JmsListtener jms消费者监听 具体的服务
     */
    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receivQueue(String text) {

        System.out.println("Consumer收到的报文: " + text);
        return "return message" + text;
    }
}
