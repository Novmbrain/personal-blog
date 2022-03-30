package com.wenjie.blog.dao;

import com.wenjie.blog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: T
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 26/03/2022
 **/
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
}
