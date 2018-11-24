package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String openId);

    @Select("select * from `User` where student_id = #{studentId}")
    User selectByStudentId(String studentId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("SELECT * FROM `Recruitment`\n" +
            "WHERE student_id = #{studentId}")
    List<Recruitment> selectCreatedRecruitment(String studentId);
}