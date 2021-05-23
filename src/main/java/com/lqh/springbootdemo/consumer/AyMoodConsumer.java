package com.lqh.springbootdemo.consumer;

import com.lqh.springbootdemo.model.AyMood;
import com.lqh.springbootdemo.service.AyMoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AyMoodConsumer {
    @Resource
    private AyMoodService ayMoodService;

    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说【"+text+"】成功！");
    }


    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(AyMood ayMood){
        ayMoodService.save(ayMood);
    }
}
