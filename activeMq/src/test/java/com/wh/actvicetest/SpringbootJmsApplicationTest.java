package com.wh.actvicetest;

import com.wh.ActiveMqApplication;
import com.wh.activeMq.ActiveMqProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.jms.Destination;

/**
 * @Description: Spring boot and ActiveMq Test
 * @author:liyujie
 * @date:2018/6/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActiveMqApplication.class)
@WebAppConfiguration
public class SpringbootJmsApplicationTest {

    @Autowired
    private ActiveMqProducer producer;

    /***
     * create ActiveMq Test
     * @throws InterruptedException
     */
    @Test
    public void contextLoads() throws InterruptedException{
        Destination destination=new ActiveMQQueue("mytest.queue");
        for (int i = 0; i <100 ; i++) {
            producer.sendMessage(destination,"my name is li "+i);
        }

    }

    @Test
    public void activeMqTest() throws InterruptedException{


    }

}
