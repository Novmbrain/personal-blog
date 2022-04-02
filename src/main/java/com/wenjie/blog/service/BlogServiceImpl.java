package com.wenjie.blog.service;

import com.wenjie.blog.dao.BlogRepository;
import com.wenjie.blog.exception.NotFoundException;
import com.wenjie.blog.pojo.Blog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: BlogServiceImpl
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 31/03/2022
 **/
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getById(id);
    }

    //@Override
    //public Page<Blog> listBlog(Pageable pageable, Blog blog) {
    //    return blogRepository.findAll(new Specification<Blog>() {
    //        @Override
    //        public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    //            List<Predicate> predicates = new ArrayList<>();
    //            if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
    //                predicates.add(criteriaBuilder.like(root.<String>get("title")), blog.getTitle());
    //            }
    //            return null;
    //        }
    //    }, pageable)；
    //    return null;
    //}

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getById(id);

        if (b == null) {
            throw new NotFoundException("This blog doesn't exist");
        }

        BeanUtils.copyProperties(blog, b);
        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
         blogRepository.deleteById(id);
    }
}
