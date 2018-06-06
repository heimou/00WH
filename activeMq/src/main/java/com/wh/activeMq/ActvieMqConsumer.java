package com.wh.activeMq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Spring boot ActiveMq cousumer
 * @author:liyujie
 * @date:2018/6/5
 */
@Component
public class ActvieMqConsumer {

    /**
     * @JmsListtener jms消费者监听 具体的服务
     * @param text
     */
    @JmsListener(destination = "mytest.queue")
    public void receivQueue(String text){
        System.out.println("Consumer收到的报文: "+text);
    }
}
