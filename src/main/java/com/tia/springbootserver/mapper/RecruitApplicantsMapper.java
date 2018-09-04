package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.RecruitApplicants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitApplicantsMapper {
    int deleteByPrimaryKey(@Param("recruitId") Integer recruitId, @Param("applicantId") String applicantId);

    int insert(RecruitApplicants record);

    int insertSelective(RecruitApplicants record);
}