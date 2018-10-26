package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.UserFocused;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFocusedMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("recruitId") Integer recruitId);

    int insert(UserFocused record);

    int insertSelective(UserFocused record);

    @Select("SELECT * FROM Recruitment\n" +
            "WHERE recruit_id IN\n" +
            "(SELECT recruit_id FROM User_Focused WHERE student_id=#{studentId})")
    List<Recruitment> selectByUserId(String studentId);

    @Select("SELECT * FROM `User_Focused`\n" +
            "WHERE recruit_id = #{recruitId} and student_id = #{studentId}")
    UserFocused selectUserFocused(@Param("recruitId")Integer recruitId,@Param("studentId") String studentId);
}