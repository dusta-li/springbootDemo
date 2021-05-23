package com.lqh.springbootdemo.controller;

import com.lqh.springbootdemo.error.BusinessException;
import com.lqh.springbootdemo.model.AyUser;
import com.lqh.springbootdemo.service.AyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ayUser")
public class AyUserController {
    @Resource
    private AyUserService ayUserService;

    @RequestMapping("/test")
    public String test(Model model){
        //查询库中所有的数据
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users",ayUser);
        return "ayUser";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){
        throw new BusinessException("业务异常");
    }

    @RequestMapping("/findByNameAndPasswordRetry")
    public String findByNameAndPasswordRetry(Model model){
        AyUser ayUser = ayUserService.findByNameAndPasswordRetry("阿毅", "123456");
        model.addAttribute("users",ayUser);
        return "sucess";
    }
}
