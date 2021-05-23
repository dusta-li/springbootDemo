package com.lqh.springbootdemo.service;

import com.lqh.springbootdemo.model.AyMood;

public interface AyMoodService {
    AyMood save(AyMood ayMood);
    String asynSave(AyMood ayMood);
}
