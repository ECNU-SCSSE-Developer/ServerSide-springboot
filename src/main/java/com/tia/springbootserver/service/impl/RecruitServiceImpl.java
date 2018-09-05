package com.tia.springbootserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.*;
import com.tia.springbootserver.mapper.*;
import com.tia.springbootserver.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "recruitService")
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Autowired
    private MatchRecruitMapper matchRecruitMapper;

    @Autowired
    private UserCreatedMapper userCreatedMapper;

    @Autowired
    private UserFocusedMapper userFocusedMapper;

    @Autowired
    private UserRegisteredMapper userRegisteredMapper;

    @Autowired
    private RecruitApplicantsMapper recruitApplicantsMapper;

    @Autowired
    UserRegistered userRegistered;

    @Autowired
    UserFocused userFocused;

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
    public int deleteRecruit(Integer recruitId) {
        return recruitmentMapper.deleteByPrimaryKey(recruitId);
    }

    @Override
    public int bindToMatch(MatchRecruit matchRecruit) {
        return matchRecruitMapper.insert(matchRecruit);
    }

    @Override
    public int unBindFromMatch(Integer recruitId) {
        return matchRecruitMapper.deleteByRecruitId(recruitId);
    }

    @Override
    public int getBindMatch(Integer recruitId) {
        return matchRecruitMapper.selectByRecruitId(recruitId);
    }

    @Override
    public int deleteRecruitFromUser(Integer recruitId) {
        recruitmentMapper.deleteFromCreated(recruitId);
        recruitmentMapper.deleteFromFocused(recruitId);
        return recruitmentMapper.deleteFromRegistered(recruitId);
    }

    @Override
    public int bindToUser(UserCreated record) {
        return userCreatedMapper.insert(record);
    }

    @Override
    public int register(RecruitApplicants record) {
        //
        Recruitment recruitment = recruitmentMapper.selectByPrimaryKey(record.getRecruitId());
        recruitment.setRegisteredNumber(recruitment.getRegisteredNumber() + 1);
        recruitmentMapper.updateByPrimaryKeySelective(recruitment);
        //
        userFocused.setRecruitId(record.getRecruitId());
        userFocused.setStudentId(record.getApplicantId());
        userFocusedMapper.insert(userFocused);
        //
        return recruitApplicantsMapper.insert(record);
    }

    @Override
    public int unregister(RecruitApplicants record) {
        //
        Recruitment recruitment = recruitmentMapper.selectByPrimaryKey(record.getRecruitId());
        recruitment.setRegisteredNumber(recruitment.getRegisteredNumber() - 1);
        recruitmentMapper.updateByPrimaryKeySelective(recruitment);
        //
        userFocusedMapper.deleteByPrimaryKey(record.getApplicantId(),record.getRecruitId());
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
