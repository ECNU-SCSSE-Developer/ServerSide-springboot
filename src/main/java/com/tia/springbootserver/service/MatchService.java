package com.tia.springbootserver.service;

import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Match;

public interface MatchService {

    PageInfo<Match> findAllMatch(Integer pageNum, Integer pageSize);

    PageInfo<Match> findMatchByName(String matchName,Integer pageNum, Integer pageSize);

    Match getMatchById(Integer matchId);

}
