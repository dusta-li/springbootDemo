package com.lqh.springbootdemo.service.impl;

import com.lqh.springbootdemo.model.AyRole;
import com.lqh.springbootdemo.repository.AyRoleRepository;
import com.lqh.springbootdemo.service.AyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户角色Service
 * @author Ay
 * @date   2017/12/2
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {

    @Resource
    private AyRoleRepository ayRoleRepository;

    @Override
    public AyRole find(String id){
        return ayRoleRepository.findById(id).get();
    }
}
