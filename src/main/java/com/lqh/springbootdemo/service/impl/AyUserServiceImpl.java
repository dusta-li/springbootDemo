package com.lqh.springbootdemo.service.impl;

import com.lqh.springbootdemo.dao.AyUserDao;
import com.lqh.springbootdemo.error.BusinessException;
import com.lqh.springbootdemo.model.AyUser;
import com.lqh.springbootdemo.repository.AyUserRepository;
import com.lqh.springbootdemo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AyUserServiceImpl implements AyUserService {

    @Resource
    private AyUserRepository ayUserRepository;
    @Resource
    private AyUserDao ayUserDao;

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public AyUser findById(String id) {
        return ayUserRepository.findById(id).get();
    }

    @Override
    public List<AyUser> findAll() {
        return ayUserRepository.findAll();
    }

    @Transactional
    @Override
    public AyUser save(AyUser ayUser) {
        return ayUserRepository.save(ayUser);
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
        logger.info("userId:"+id+"用户被删除");
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<String> ids) {
        return ayUserRepository.findByIdIn(ids);
    }

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name,password);
    }

    @Override
    @Retryable(value={BusinessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000,multiplier = 2))
    public AyUser findByNameAndPasswordRetry(String name, String password) {
        System.out.println("findByNameAndPasswordRetry[方法重试失败！！！]");
        throw new BusinessException();
    }

    @Override
    public AyUser findByUserName(String name) {
        List<AyUser> ayUsers = findByName(name);
        if(ayUsers == null || ayUsers.size() <= 0)
            return null;
        return ayUsers.get(0);
    }
}
