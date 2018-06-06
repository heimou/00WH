package com.wh.actviceMqController;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;


/**
 * @Description: create Web  ProducerController
 * @author:liyujie
 * @date:2018/6/6
 */
@RestController
@RequestMapping("/test")
public class Producer {


    @Autowired
    private JmsTemplate jmsMessagingTemplate;

    /**
     * 发送消息到队列中
     * @param message
     */
    @GetMapping("/sendMsg")
    public void sendMsg(String message) {
        Destination destination = new ActiveMQQueue("my.queue");
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
