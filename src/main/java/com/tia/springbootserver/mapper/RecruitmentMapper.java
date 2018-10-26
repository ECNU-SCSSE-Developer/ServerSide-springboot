package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.Match;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.returnType.simpleMatch;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentMapper {
    int deleteByPrimaryKey(Integer recruitId);

    int insert(Recruitment record);

    int insertSelective(Recruitment record);

    Recruitment selectByPrimaryKey(Integer recruitId);

    int updateByPrimaryKeySelective(Recruitment record);

    int updateByPrimaryKey(Recruitment record);

    @Select("SELECT * FROM `Recruitment` \n" +
            "WHERE recruit_name LIKE \"%${recruitName}%\"")
    List<Recruitment> selectByName(@Param(value = "recruitName") String recruitName);

    @Select("SELECT * FROM `Recruitment`")
    List<Recruitment> selectAll();

    @Delete("DELETE FROM `User_Focused`\n" +
            "WHERE recruit_id = #{recruitId}")
    int deleteFromFocused(Integer recruitId);

    @Delete("DELETE FROM `User_Registered`\n" +
            "WHERE recruit_id = #{recruitId}")
    int deleteFromRegistered(Integer recruitId);

    @Delete("DELETE FROM `Recruit_Applicants`\n" +
            "WHERE recruit_id = #{recruitId}")
    int deleteFromApply(Integer recruitId);


    @Select("SELECT match_id,match_name FROM Recruitment\n" +
            "WHERE recruit_id = #{recruitId}\n")
    simpleMatch selectBindMatch(Integer recruitId);


}