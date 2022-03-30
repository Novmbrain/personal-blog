package com.wenjie.blog.dao;

import com.wenjie.blog.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: T
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 26/03/2022
 **/
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
