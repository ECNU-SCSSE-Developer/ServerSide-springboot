package com.tia.springbootserver.service;

import com.tia.springbootserver.entity.User;

public interface UserService {
    User getById(String studentId);
}
