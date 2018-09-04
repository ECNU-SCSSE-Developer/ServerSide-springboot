package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.RecruitType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitTypeMapper {
    int deleteByPrimaryKey(@Param("recruitId") Integer recruitId, @Param("recruitType") String recruitType);

    int insert(RecruitType record);

    int insertSelective(RecruitType record);
}