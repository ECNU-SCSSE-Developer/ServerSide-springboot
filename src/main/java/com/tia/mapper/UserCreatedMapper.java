package com.tia.mapper;

import com.tia.entity.UserCreated;
import org.apache.ibatis.annotations.Param;

public interface UserCreatedMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserCreated record);

    int insertSelective(UserCreated record);
}