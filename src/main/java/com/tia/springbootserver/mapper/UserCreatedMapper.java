package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.UserCreated;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCreatedMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserCreated record);

    int insertSelective(UserCreated record);
}