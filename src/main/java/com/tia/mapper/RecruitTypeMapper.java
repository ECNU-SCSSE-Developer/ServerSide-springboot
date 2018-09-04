package com.tia.mapper;

import com.tia.entity.RecruitType;
import org.apache.ibatis.annotations.Param;

public interface RecruitTypeMapper {
    int deleteByPrimaryKey(@Param("recruitId") Integer recruitId, @Param("recruitType") String recruitType);

    int insert(RecruitType record);

    int insertSelective(RecruitType record);
}