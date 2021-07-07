package com.lqh.springbootdemo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lqh.springbootdemo.api.AyUserDubboService;
import com.lqh.springbootdemo.domain.AyUser;

@Service(version = "1.0")
public class AyUserDubboServiceImpl implements AyUserDubboService {
    @Override
    public AyUser findByUserNameAndPassword(String name, String password) {
        AyUser ayUser = new AyUser();
        ayUser.setId("1");
        ayUser.setName("ayi");
        ayUser.setPassword("123456");
        return ayUser;
    }
}
