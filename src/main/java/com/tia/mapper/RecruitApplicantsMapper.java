package com.tia.mapper;

import com.tia.entity.RecruitApplicants;
import org.apache.ibatis.annotations.Param;

public interface RecruitApplicantsMapper {
    int deleteByPrimaryKey(@Param("recruitId") Integer recruitId, @Param("applicantId") String applicantId);

    int insert(RecruitApplicants record);

    int insertSelective(RecruitApplicants record);
}