package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}