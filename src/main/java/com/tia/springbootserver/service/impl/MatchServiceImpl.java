package com.tia.springbootserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Match;
import com.tia.springbootserver.mapper.MatchMapper;
import com.tia.springbootserver.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "matchService")
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public PageInfo<Match> findAllMatch(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Match> matchList = matchMapper.selectAll();
        PageInfo result = new PageInfo(matchList);
        return result;
    }

    @Override
    public Match getMatchById(Integer matchId){
        return matchMapper.selectByPrimaryKey(matchId);
    }

    @Override
    public PageInfo<Match> findMatchByName(String matchName,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Match> matchList = matchMapper.selectByName(matchName);
        PageInfo result = new PageInfo(matchList);
        return result;
    }
}
