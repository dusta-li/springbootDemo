package com.lqh.springbootdemo.service.impl;

import com.lqh.springbootdemo.model.AyMood;
import com.lqh.springbootdemo.producer.AyMoodProducer;
import com.lqh.springbootdemo.repository.AyMoodRepository;
import com.lqh.springbootdemo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
@Transactional
public class AyMoodServiceImpl implements AyMoodService {
    @Resource
    private AyMoodRepository ayMoodRepository;
    @Resource
    private AyMoodProducer ayMoodProducer;

    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");//队列

    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    @Override
    public String asynSave(AyMood ayMood) {
        ayMoodProducer.sendMessage(destination,ayMood);
        return "sucess";
    }
}
