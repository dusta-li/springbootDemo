package com.lqh.springbootdemo.repository;

import com.lqh.springbootdemo.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AyUserRepository extends JpaRepository<AyUser,String> {

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
}
