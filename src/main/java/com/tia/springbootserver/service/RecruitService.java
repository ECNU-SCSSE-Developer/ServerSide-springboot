package com.tia.springbootserver.service;

import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.*;
import com.tia.springbootserver.entity.returnType.simpleMatch;

import java.util.List;


public interface RecruitService {
    int createRecruit(Recruitment recruitment);
    int createRecruitWithId(Recruitment recruitment);
    int updateRecruitInfo(Recruitment recruitment);
    PageInfo<Recruitment> findAllRecruit(Integer pageNum, Integer pageSize);
    List<Recruitment> findAllRecruitNotOnPage();
    Recruitment findRecruitById(Integer recruitId);
    PageInfo<Recruitment> findRecruitByName(String recruitName,Integer pageNum, Integer pageSize);
    List<Recruitment> findRecruitByNameNotOnPage(String recruitName);
    int deleteRecruit(Integer recruitId);
    int bindToMatch(Integer recruitId, Integer matchId, String matchName);
    simpleMatch getBindMatch(Integer recruitId);
    int deleteRecruitFromUser(Integer recruitId);
    int register(RecruitApplicants record);
    int unregister(RecruitApplicants record);
    PageInfo<User> getApplicantsInfo(Integer recruitId, Integer pageNum, Integer pageSize);
    List<User> getApplicantsInfoNotOnPage(Integer recruitId);

    List<Recruitment> findRecruitByType(String type);
    int addTypeForRecruit(RecruitType record);
    int deleteTypeForRecruit(RecruitType record);

}
