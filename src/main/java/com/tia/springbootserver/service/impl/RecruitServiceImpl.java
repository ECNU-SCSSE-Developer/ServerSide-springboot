package com.tia.springbootserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.*;
import com.tia.springbootserver.entity.returnType.simpleMatch;
import com.tia.springbootserver.mapper.*;
import com.tia.springbootserver.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service(value = "recruitService")
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    private RecruitmentMapper recruitmentMapper;
    @Autowired
    private  RecruitTypeMapper recruitTypeMapper;

    @Autowired
    private UserFocusedMapper userFocusedMapper;

    @Autowired
    private UserRegisteredMapper userRegisteredMapper;

    @Autowired
    private RecruitApplicantsMapper recruitApplicantsMapper;

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    UserRegistered userRegistered;

    @Autowired
    UserFocused userFocused;

    @Autowired
    Recruitment recruitment;

    @Override
    public int createRecruitWithId(Recruitment recruitment) {
        return recruitmentMapper.insert(recruitment);
    }

    @Override
    public List<Recruitment> findRecruitByMatchId(String matchName) {
        return recruitmentMapper.selectByMatchName(matchName);
    }

    @Override
    public List<String> findAllRecruitType() {
        return recruitTypeMapper.findAllType();
    }

    @Override
    public int createRecruit(Recruitment recruitment) {
        return recruitmentMapper.insertSelective(recruitment);
    }

    @Override
    public int updateRecruitInfo(Recruitment recruitment) {
        return recruitmentMapper.updateByPrimaryKeySelective(recruitment);
    }

    @Override
    public Recruitment findRecruitById(Integer recruitId) {
        return recruitmentMapper.selectByPrimaryKey(recruitId);
    }

    @Override
    public PageInfo<Recruitment> findRecruitByName(String recruitName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Recruitment> result = new PageInfo(recruitmentMapper.selectByName(recruitName));
        return result;
    }

    @Override
    public List<Recruitment> findRecruitByNameNotOnPage(String recruitName) {
        return recruitmentMapper.selectByName(recruitName);
    }

    @Override
    public PageInfo<Recruitment> findAllRecruit(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Recruitment> result = new PageInfo(recruitmentMapper.selectAll());
        return result;
    }

    @Override
    public List<Recruitment> findAllRecruitNotOnPage() {
        return recruitmentMapper.selectAll();
    }


    @Override
    public int deleteRecruit(Integer recruitId) {
        return recruitmentMapper.deleteByPrimaryKey(recruitId);
    }

    @Override
    public int bindToMatch(Integer recruitId, Integer matchId, String matchName) {
        recruitment.setRecruitId(recruitId);
        recruitment.setMatchId(matchId);
        recruitment.setMatchName(matchName);
        return recruitmentMapper.updateByPrimaryKeySelective(recruitment);
    }

    @Override
    public simpleMatch getBindMatch(Integer recruitId) {
        return recruitmentMapper.selectBindMatch(recruitId);
    }

    @Override
    public int deleteRecruitFromUser(Integer recruitId) {
        recruitmentMapper.deleteFromFocused(recruitId);
        recruitmentMapper.deleteFromApply(recruitId);
        recruitmentMapper.deleteFromRegistered(recruitId);
        return recruitmentMapper.deleteFromRegistered(recruitId);
    }


    // 这里的register应该是apply的意思
    @Override
    public int register(RecruitApplicants record) {
        //
        Recruitment recruitment = recruitmentMapper.selectByPrimaryKey(record.getRecruitId());
        recruitment.setWillingNumber(recruitment.getWillingNumber()+1);
        recruitmentMapper.updateByPrimaryKeySelective(recruitment);
        //
        userFocused.setRecruitId(record.getRecruitId());
        userFocused.setStudentId(record.getApplicantId());
        if (userFocusedMapper.selectUserFocused(record.getRecruitId(),record.getApplicantId())==null)
            userFocusedMapper.insert(userFocused);
        if(recruitApplicantsMapper.selectRecruitApplicants(record.getRecruitId(),record.getApplicantId())==null)
            recruitApplicantsMapper.insert(record);
        return 0;
    }

    @Override
    public int unregister(RecruitApplicants record) {
        //
        Recruitment recruitment = recruitmentMapper.selectByPrimaryKey(record.getRecruitId());
        recruitment.setWillingNumber(recruitment.getWillingNumber()-1);
        recruitmentMapper.updateByPrimaryKeySelective(recruitment);
        //
        return recruitApplicantsMapper.deleteByPrimaryKey(record.getRecruitId(),record.getApplicantId());
    }

    @Override
    public PageInfo<User> getApplicantsInfo(Integer recruitId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> result = new PageInfo(recruitApplicantsMapper.selectUserByRecruitId(recruitId));
        return result;
    }

    @Override
    public List<User> getApplicantsInfoNotOnPage(Integer recruitId) {
        return recruitApplicantsMapper.selectUserByRecruitId(recruitId);
    }

    @Override
    public List<Recruitment> findRecruitByType(String type) {
        List<Recruitment> result = new ArrayList<>();
        Set<Integer> selectedRecruitId = new HashSet<>();
        String[] temp = type.split(";");
        if (temp!=null){
            for (String each : temp){
                for(RecruitType tempResult:recruitTypeMapper.findRecruitByType(each)){
                    selectedRecruitId.add(tempResult.getRecruitId());
                }
            }
        }
        else{
            for(RecruitType tempResult:recruitTypeMapper.findRecruitByType(type)){
                selectedRecruitId.add(tempResult.getRecruitId());
            }
        }
        for (Integer recruitId : selectedRecruitId){
            result.add(recruitmentMapper.selectByPrimaryKey(recruitId));
        }
        return result;
    }

    @Override
    public int addTypeForRecruit(RecruitType record) {
        List<Recruitment> result = new ArrayList<>();
        String[] temp = record.getRecruitType().split(";");

        if (temp==null){
            recruitTypeMapper.insert(record);
        }
        else{
            for (String each : temp){
                RecruitType recruitType = new RecruitType();
                recruitType.setRecruitId(record.getRecruitId());
                recruitType.setRecruitType(each);
                recruitTypeMapper.insert(recruitType);
            }

        }
        return 0;


    }


    @Override
    public int deleteTypeForRecruit(RecruitType record) {
        return recruitTypeMapper.deleteByRecord(record);
    }


}
