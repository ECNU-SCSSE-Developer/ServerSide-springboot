package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.RecruitType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitTypeMapper {
    int deleteByPrimaryKey(@Param("recruitId") Integer recruitId, @Param("recruitType") String recruitType);

    int insert(RecruitType record);

    int insertSelective(RecruitType record);


    @Select("SELECT DISTINCT `recruit_type` FROM `Recruit_Type`")
    List<String> findAllType();

    @Select("SELECT * FROM `Recruit_Type` WHERE recruit_type = #{type}")
    List<RecruitType> findRecruitByType(String type);

    @Select("SELECT * FROM `Recruit_Type` WHERE recruit_id = #{recruitId}")
    RecruitType findRecruitByRecruitId(Integer recruitId);


    @Delete("DELETE FROM `Recruit_Type` WHERE \n" +
            "`recruit_id` = #{recruitId} and `recruit_type` like #{recruitType} \n")
    int deleteByRecord(RecruitType record);
}