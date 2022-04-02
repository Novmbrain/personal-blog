package com.wenjie.blog.service;

import com.wenjie.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @className: BlogService
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 31/03/2022
 **/
public interface BlogService {

    Blog getBlog(Long id);

    /*
    * 多传递一个 Blog 对象记录筛选条件*/
    Page<Blog> listBlog(Pageable pageable, Blog blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
