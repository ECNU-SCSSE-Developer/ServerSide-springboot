package com.tia.mapper;

import com.tia.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

public interface UserRegisteredMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserRegistered record);

    int insertSelective(UserRegistered record);
}