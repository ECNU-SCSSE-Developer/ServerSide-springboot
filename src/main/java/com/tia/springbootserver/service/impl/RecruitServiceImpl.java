package com.tia.springbootserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.*;
import com.tia.springbootserver.entity.returnType.simpleMatch;
import com.tia.springbootserver.mapper.*;
import com.tia.springbootserver.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "recruitService")
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    private RecruitmentMapper recruitmentMapper;

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
    public PageInfo<Recruitment> findAllRecruit(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Recruitment> result = new PageInfo(recruitmentMapper.selectAll());
        return result;
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



}
