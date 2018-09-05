package com.tia.springbootserver.service;

import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.*;


public interface RecruitService {
    int createRecruit(Recruitment recruitment);
    int createRecruitWithId(Recruitment recruitment);
    int updateRecruitInfo(Recruitment recruitment);
    Recruitment findRecruitById(Integer recruitId);
    PageInfo<Recruitment> findRecruitByName(String recruitName,Integer pageNum, Integer pageSize);
    int deleteRecruit(Integer recruitId);
    int bindToMatch(MatchRecruit matchRecruit);
    int unBindFromMatch(Integer recruitId);
    int getBindMatch(Integer  recruitId);
    int deleteRecruitFromUser(Integer recruitId);
    int bindToUser(UserCreated userCreated);
    int register(RecruitApplicants record);
    int unregister(RecruitApplicants record);
    PageInfo<User> getApplicantsInfo(Integer recruitId, Integer pageNum, Integer pageSize);

}
