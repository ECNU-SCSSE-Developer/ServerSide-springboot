package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.MatchType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchTypeMapper {
    int deleteByPrimaryKey(@Param("matchId") Integer matchId, @Param("matchType") String matchType);

    int insert(MatchType record);

    int insertSelective(MatchType record);
}