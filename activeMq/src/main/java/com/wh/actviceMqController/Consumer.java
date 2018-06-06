package com.wh.actviceMqController;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Description: create Consumer
 * @author:liyujie
 * @date:2018/6/6
 */
@Component
public class Consumer {

    @JmsListener(destination = "my.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer:"+text);
    }
}
