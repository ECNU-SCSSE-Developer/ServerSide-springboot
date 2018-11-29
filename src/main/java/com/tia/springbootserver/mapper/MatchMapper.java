package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.Match;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchMapper {
    int deleteByPrimaryKey(Integer matchId);

    int insert(Match record);

    int insertSelective(Match record);

    Match selectByPrimaryKey(Integer matchId);

    @Select("SELECT * FROM `Match`")
    List<Match> selectAll();

    // TODO: 潜在的SQL注入攻击，应在执行前检查参数
    @Select("Select * FROM `Match` WHERE match_name like \"%${matchName}%\" ")
    List<Match> selectByName(@Param(value = "matchName") String matchName);

    int updateByPrimaryKeySelective(Match record);

    int updateByPrimaryKey(Match record);

}