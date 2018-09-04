package com.tia.mapper;

import com.tia.entity.MatchRecruit;
import org.apache.ibatis.annotations.Param;

public interface MatchRecruitMapper {
    int deleteByPrimaryKey(@Param("matchId") Integer matchId, @Param("recruitId") Integer recruitId);

    int insert(MatchRecruit record);

    int insertSelective(MatchRecruit record);
}