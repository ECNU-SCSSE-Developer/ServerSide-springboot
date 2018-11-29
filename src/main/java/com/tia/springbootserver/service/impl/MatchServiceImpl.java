package com.tia.springbootserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Match;
import com.tia.springbootserver.entity.MatchType;
import com.tia.springbootserver.mapper.MatchMapper;
import com.tia.springbootserver.mapper.MatchTypeMapper;
import com.tia.springbootserver.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "matchService")
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private MatchTypeMapper matchTypeMapper;


    @Override
    public PageInfo<Match> findAllMatch(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Match> matchList = matchMapper.selectAll();
        return new PageInfo(matchList);
    }

    @Override
    public List<Match> findAllMatchNotOnPage() {
        List<Match> matchList = matchMapper.selectAll();
        return matchList;
    }

    @Override
    public Match getMatchById(Integer matchId){
        Match match = matchMapper.selectByPrimaryKey(matchId);
        return match;
    }

    @Override
    public List<Match> findMatchByType(String type) {

        String[] typeList = type.split(";");
        List<MatchType> selectedType = new ArrayList<>();
        List<Match> result = new ArrayList<>();
        for (String each : typeList){
            selectedType.addAll(matchTypeMapper.selectByType(each));
        }
        for (MatchType eachMatch : selectedType){
            result.add(matchMapper.selectByPrimaryKey(eachMatch.getMatchId()));
        }
        return result;

    }


    @Override
    public PageInfo<Match> findMatchByName(String matchName,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Match> matchList = matchMapper.selectByName(matchName);
        return new PageInfo(matchList);
    }

    @Override
    public List<Match> findMatchByNameNotOnPage(String matchName) {
        List<Match> matchList =  matchMapper.selectByName(matchName);
        return matchList;
    }



}
