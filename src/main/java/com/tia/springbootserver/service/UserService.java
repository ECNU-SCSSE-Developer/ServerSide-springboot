package com.tia.springbootserver.service;

import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.entity.returnType.UserWithSplitContacts;

import java.util.List;

public interface UserService {

    User getById(String studentId);
    UserWithSplitContacts getByIdWithSplitContacts(String studentId);
    int insertUser(User user);
    int updateUserInfo(User user);
    PageInfo<Recruitment> getFocusedRecruitment(String studentId,Integer pageNum, Integer pageSize);
    List<Recruitment> getFocusedRecruitmentNotOnPage(String studentId);
    PageInfo<Recruitment> getRegisteredRecruitment(String studentId,Integer pageNum, Integer pageSize);
    List<Recruitment> getRegisteredRecruitmentNotOnPage(String studentId);
    PageInfo<Recruitment> getCreatedRecruitment(String studentId,Integer pageNum, Integer pageSize);
    List<Recruitment> getCreatedRecruitmentNotOnPage(String studentId);
    List<Recruitment> getAppliedRecruitmentNotOnPage(String studentId);
    int acceptUser(Integer recruitId, String applicantId);
    int cancelAcceptUser(Integer recruitId, String applicantId);
    int addFocusedRecruitment(String studentId, Integer recruitId);
    int deleteFocusedRecruitment(String studentId, Integer recruitId);
}
