package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.RecruitApplicants;
import com.tia.springbootserver.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitApplicantsMapper {
    int deleteByPrimaryKey(@Param("recruitId") Integer recruitId, @Param("applicantId") String applicantId);

    int insert(RecruitApplicants record);

    int insertSelective(RecruitApplicants record);

    @Select("SELECT * FROM `User`\n" +
            "WHERE student_id IN \n" +
            "(SELECT applicant_id FROM `Recruit_Applicants` WHERE recruit_id = #{recruitId})")
    List<User> selectUserByRecruitId(Integer recruitId);
}