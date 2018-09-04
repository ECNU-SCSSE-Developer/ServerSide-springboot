package com.tia.mapper;

import com.tia.entity.Match;

public interface MatchMapper {
    int deleteByPrimaryKey(Integer matchId);

    int insert(Match record);

    int insertSelective(Match record);

    Match selectByPrimaryKey(Integer matchId);

    int updateByPrimaryKeySelective(Match record);

    int updateByPrimaryKey(Match record);
}