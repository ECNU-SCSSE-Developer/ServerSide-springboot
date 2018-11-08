package com.tia.springbootserver.service;

import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Match;

import java.util.List;

public interface MatchService {

    PageInfo<Match> findAllMatch(Integer pageNum, Integer pageSize);

    List<Match> findAllMatchNotOnPage();

    PageInfo<Match> findMatchByName(String matchName,Integer pageNum, Integer pageSize);

    List<Match> findMatchByNameNotOnPage(String matchName);

    Match getMatchById(Integer matchId);

}
