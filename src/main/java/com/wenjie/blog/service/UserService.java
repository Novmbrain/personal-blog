package com.wenjie.blog.service;

import com.wenjie.blog.pojo.User;

/**
 * @className: UserService
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 21/03/2022
 **/
public interface UserService {
    public User checkUser(String userName, String password);
}
