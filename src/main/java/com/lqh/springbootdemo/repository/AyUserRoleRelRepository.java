package com.lqh.springbootdemo.repository;


import com.lqh.springbootdemo.model.AyUserRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 描述：用户角色关联Repository
 * @author Ay
 * @date   2017/10/14.
 */
public interface AyUserRoleRelRepository extends JpaRepository<AyUserRoleRel,String> {

    List<AyUserRoleRel> findByUserId(@Param("userId") String userID);
}
