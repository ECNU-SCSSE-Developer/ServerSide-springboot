package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.UserFocused;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFocusedMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserFocused record);

    int insertSelective(UserFocused record);
}