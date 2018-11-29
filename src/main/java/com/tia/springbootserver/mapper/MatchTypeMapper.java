package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.MatchType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchTypeMapper {
    int deleteByPrimaryKey(@Param("matchId") Integer matchId, @Param("matchType") String matchType);

    int insert(MatchType record);

    int insertSelective(MatchType record);

    @Select("SELECT * FROM `Match_Type` WHERE `match_type` = #{type}")
    List<MatchType> selectByType(String type);
}