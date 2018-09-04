package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.Recruitment;

public interface RecruitmentMapper {
    int deleteByPrimaryKey(Integer recruitId);

    int insert(Recruitment record);

    int insertSelective(Recruitment record);

    Recruitment selectByPrimaryKey(Integer recruitId);

    int updateByPrimaryKeySelective(Recruitment record);

    int updateByPrimaryKey(Recruitment record);
}