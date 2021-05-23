package com.lqh.springbootdemo.repository;

import com.lqh.springbootdemo.model.AyRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述：用户角色Repository
 * @author Ay
 * @date   2017/12/10.
 */
public interface AyRoleRepository extends JpaRepository<AyRole,String> {

}
