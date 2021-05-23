package com.lqh.springbootdemo.service;

import com.lqh.springbootdemo.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface AyUserService {

    AyUser findById(String id);
    List<AyUser> findAll();
    AyUser save(AyUser ayUser);
    void delete(String id);

    //分页
    Page<AyUser> findAll(Pageable pageable);

    /**
     * select u from ay_user u where u.name = ?
     * @param name
     * @return
     */
    List<AyUser> findByName(String name);

    /**
     * select u from ay_user u where u.name like ?
     * @param name
     * @return
     */
    List<AyUser> findByNameLike(String name);

    /**
     * select u from ay_user u where id in(?,?,?)
     * @param ids
     * @return
     */
    List<AyUser> findByIdIn(Collection<String> ids);

    AyUser findByNameAndPassword(String name,String password);

    AyUser findByNameAndPasswordRetry(String name,String password);

    AyUser findByUserName(String name);
}
