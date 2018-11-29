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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Integer> selectedTypeId = new HashSet<>();
        List<Match> result = new ArrayList<>();
        if (typeList!=null){
            for (String each : typeList){
                for (MatchType tempResult: matchTypeMapper.selectByType(each))
                    selectedTypeId.add(tempResult.getMatchId());
            }
        }
        else{
            for (MatchType tempResult: matchTypeMapper.selectByType(type))
                selectedTypeId.add(tempResult.getMatchId());
        }
        for (Integer eachMatchId : selectedTypeId){
            result.add(matchMapper.selectByPrimaryKey(eachMatchId));
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
