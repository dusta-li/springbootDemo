package com.lqh.springbootdemo.producer;


import com.lqh.springbootdemo.model.AyMood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class AyMoodProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;//发消息的工具类

    /**
     *
     * @param destination 发送到的队列
     * @param message 待发送的消息
     */
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    public void sendMessage(Destination destination, final AyMood ayMood){
        jmsMessagingTemplate.convertAndSend(destination,ayMood);
    }
}
