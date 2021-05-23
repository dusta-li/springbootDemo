package com.lqh.springbootdemo.repository;

import com.lqh.springbootdemo.model.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AyMoodRepository extends JpaRepository<AyMood,String> {
}
