package com.wenjie.blog.service;

import com.wenjie.blog.dao.UserRepository;
import com.wenjie.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @className: UserServiceImpl
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 21/03/2022
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String password) {
        User user = userRepository.findByUserNameAndPassword(userName, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
