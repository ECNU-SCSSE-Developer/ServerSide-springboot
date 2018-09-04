package com.tia.mapper;

import com.tia.entity.UserEvaluation;

public interface UserEvaluationMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(UserEvaluation record);

    int insertSelective(UserEvaluation record);

    UserEvaluation selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(UserEvaluation record);

    int updateByPrimaryKey(UserEvaluation record);
}