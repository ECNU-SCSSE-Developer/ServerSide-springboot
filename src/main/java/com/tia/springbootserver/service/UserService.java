package com.tia.springbootserver.service;

import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.User;

public interface UserService {

    User getById(String studentId);
    int insertUser(User user);
    int updateUserInfo(User user);
    PageInfo<Recruitment> getFocusedRecruitment(String studentId,Integer pageNum, Integer pageSize);
    PageInfo<Recruitment> getRegisteredRecruitment(String studentId,Integer pageNum, Integer pageSize);
    PageInfo<Recruitment> getCreatedRecruitment(String studentId,Integer pageNum, Integer pageSize);
    int acceptUser(Integer recruitId, String applicantId);
    int addFocusedRecruitment(String studentId, Integer recruitId);
    int deleteFocusedRecruitment(String studentId, Integer recruitId);
}
