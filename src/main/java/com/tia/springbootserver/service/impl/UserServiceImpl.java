package com.tia.springbootserver.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.entity.UserFocused;
import com.tia.springbootserver.entity.UserRegistered;
import com.tia.springbootserver.entity.returnType.UserWithSplitContacts;
import com.tia.springbootserver.mapper.RecruitApplicantsMapper;
import com.tia.springbootserver.mapper.UserFocusedMapper;
import com.tia.springbootserver.mapper.UserMapper;
import com.tia.springbootserver.mapper.UserRegisteredMapper;
import com.tia.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFocusedMapper userFocusedMapper;


    @Autowired
    private UserRegisteredMapper userRegisteredMapper;

    @Autowired
    private RecruitApplicantsMapper recruitApplicantsMapper;


    @Autowired
    private UserRegistered userRegistered;

    @Autowired
    private UserFocused userFocused;

    @Override
    public User getById(String studentId) {
        return userMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public UserWithSplitContacts getByIdWithSplitContacts(String studentId) {
        UserWithSplitContacts temp = new UserWithSplitContacts(userMapper.selectByPrimaryKey(studentId));
        String[] contactsTemp = temp.getContacts().split(";");
        temp.setQq(contactsTemp[0]);
        temp.setEmail(contactsTemp[1]);
        return temp;
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageInfo<Recruitment> getFocusedRecruitment(String studentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Recruitment> result = new PageInfo(userFocusedMapper.selectByUserId(studentId));
        return result;

    }

    @Override
    public List<Recruitment> getFocusedRecruitmentNotOnPage(String studentId) {
        return userFocusedMapper.selectByUserId(studentId);
    }

    @Override
    public PageInfo<Recruitment> getRegisteredRecruitment(String studentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Recruitment> result = new PageInfo(userRegisteredMapper.selectByUserId(studentId));
        return result;
    }

    @Override
    public List<Recruitment> getRegisteredRecruitmentNotOnPage(String studentId) {
        return userRegisteredMapper.selectByUserId(studentId);
    }

    @Override
    public PageInfo<Recruitment> getCreatedRecruitment(String studentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Recruitment> result = new PageInfo(userMapper.selectCreatedRecruitment(studentId));
        return result;
    }

    @Override
    public List<Recruitment> getCreatedRecruitmentNotOnPage(String studentId) {
        return userMapper.selectCreatedRecruitment(studentId);
    }

    @Override
    public List<Recruitment> getAppliedRecruitmentNotOnPage(String studentId) {
        return recruitApplicantsMapper.selectRecruitByStudentId(studentId);
    }

    @Override
    public int acceptUser(Integer recruitId, String applicantId) {
        userRegistered.setStudentId(applicantId);
        userRegistered.setRecruitId(recruitId);
        return userRegisteredMapper.insert(userRegistered);
    }

    @Override
    public int cancelAcceptUser(Integer recruitId, String applicantId) {
        return userRegisteredMapper.deleteByPrimaryKey(applicantId,recruitId);
    }



    @Override
    public int addFocusedRecruitment(String studentId, Integer recruitId) {
        userFocused.setStudentId(studentId);
        userFocused.setRecruitId(recruitId);
        return userFocusedMapper.insert(userFocused);
    }

    @Override
    public int deleteFocusedRecruitment(String studentId, Integer recruitId) {
        return userFocusedMapper.deleteByPrimaryKey(studentId,recruitId);
    }

}
