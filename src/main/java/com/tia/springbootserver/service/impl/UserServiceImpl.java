package com.tia.springbootserver.service.impl;

import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.mapper.UserMapper;
import com.tia.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(String studentId) {
        return userMapper.selectByPrimaryKey(studentId);
    }
}
