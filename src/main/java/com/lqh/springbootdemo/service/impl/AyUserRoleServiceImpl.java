package com.lqh.springbootdemo.service.impl;

import com.lqh.springbootdemo.model.AyUserRoleRel;
import com.lqh.springbootdemo.repository.AyUserRoleRelRepository;
import com.lqh.springbootdemo.service.AyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：用户角色关联Service
 * @author   Ay
 * @date     2017/12/10.
 */
@Service
public class AyUserRoleServiceImpl implements AyUserRoleRelService {

    @Resource
    private AyUserRoleRelRepository ayUserRoleRelRepository;

    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId);
    }
}
