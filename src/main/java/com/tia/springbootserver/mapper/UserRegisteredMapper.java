package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegisteredMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserRegistered record);

    int insertSelective(UserRegistered record);

    @Select("SELECT * FROM Recruitment \n" +
            "WHERE recruit_id IN\n" +
            "(SELECT recruit_id FROM User_Registered WHERE student_id=#{studentId})")
    List<Recruitment> selectByUserId(String studentId);
}