package com.wenjie.blog.dao;

import com.wenjie.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: UserRepository
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 21/03/2022
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameAndPassword(String userName, String password);
}
