package com.wenjie.blog.dao;

import com.wenjie.blog.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @className: BlogRepository
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 31/03/2022
 **/
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
}
