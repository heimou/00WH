package com.wh.activeMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


/**
 * @Description: Spring boot 整合 ActiveMq
 * @author:liyujie
 * @date:2018/6/5
 */
@Service("activeMqProducer")
public class ActiveMqProducer {

    /**
     * JmsMessagingTemplate  Jms消息模板
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     *
     * @param destination 发送的队列
     * @param message 待发送的消息
     */
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
