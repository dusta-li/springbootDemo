package com.lqh.springbootdemo.api;

import com.lqh.springbootdemo.domain.AyUser;

public interface AyUserDubboService {

    AyUser findByUserNameAndPassword(String name,String password);
}
