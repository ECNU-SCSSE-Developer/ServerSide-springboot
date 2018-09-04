package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisteredMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserRegistered record);

    int insertSelective(UserRegistered record);
}