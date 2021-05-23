package com.lqh.springbootdemo.dao;

import com.lqh.springbootdemo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AyUserDao {

    AyUser findByNameAndPassword(@Param("name") String name,@Param("password") String password);
}
