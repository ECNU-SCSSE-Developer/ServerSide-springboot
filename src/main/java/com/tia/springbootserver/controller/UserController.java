package com.tia.springbootserver.controller;


import com.tia.springbootserver.entity.RecruitApplicants;
import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.mapper.UserFocusedMapper;
import com.tia.springbootserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;

@RestController
@EnableWebMvc
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFocusedMapper userFocusedMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping(value = "/tia/user",produces = {"application/json;charset=UTF-8"})
    public Object getUserByIdWithSplitContacts(@RequestParam(value = "studentId") String studentId,@RequestParam(value = "isSplit",defaultValue = "1") Integer isSplit){
        if (isSplit==1)
            return userService.getByIdWithSplitContacts(studentId);
        else
            return userService.getByStudentId(studentId);
    }

    @GetMapping(value = "/tia/user/oid", produces = {"application/json;charset=UTF-8"})
    public Object getUserByOpenId(String openId){
        return userService.getByOpenId(openId);
    }




    @PostMapping(value = "/tia/user")
    public Object createUser(@RequestBody User user, @RequestAttribute(value = "openId")String openId)
    {
        System.out.println(openId);
        user.setOpenId(openId);
        System.out.println(user.toString());
        return userService.insertUser(user);
    }

    @PutMapping(value = "/tia/user",produces = {"application/json;charset=UTF-8"})
    public Object updateUserInfo(@RequestBody User user, @RequestAttribute(value = "openId")String openId){
        System.out.println(openId);
        user.setOpenId(openId);
        System.out.println(user.toString());
        return userService.updateUserInfo(user);
    }

    @GetMapping(value = "/tia/user/focused", produces = {"application/json;charset=UTF-8"})
    public Object getFocusedRecruitment(String studentId,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                    Integer pageSize,
                                        @RequestParam(name = "onPage", required = false, defaultValue = "-1")
                                                    Integer onPage){
        if (onPage!=-1)
            return userService.getFocusedRecruitment(studentId,pageNum,pageSize);
        else
            return userService.getFocusedRecruitmentNotOnPage(studentId);
    }

    @PutMapping(value = "/tia/user/focused")
    public Object addFocusedRecruitment(@RequestBody RecruitApplicants recruitApplicants){
        logger.info(recruitApplicants.toString());
        if(userFocusedMapper.selectUserFocused(recruitApplicants.getRecruitId(),recruitApplicants.getApplicantId())==null)
            return userService.addFocusedRecruitment(recruitApplicants.getApplicantId(),recruitApplicants.getRecruitId());
        return 0;
    }

    @DeleteMapping(value = "/tia/user/focused", produces = {"application/json;charset=UTF-8"})
    public Object deleteFocusedRecruitment(@RequestBody RecruitApplicants recruitApplicants){
        return userService.deleteFocusedRecruitment(recruitApplicants.getApplicantId(), recruitApplicants.getRecruitId());
    }

    @GetMapping(value = "/tia/user/applied", produces = {"application/json;charset=UTF-8"})
    public Object getAppliedRecruitment(String studentId){
        return userService.getAppliedRecruitmentNotOnPage(studentId);
    }


    @GetMapping(value = "/tia/user/registered", produces = {"application/json;charset=UTF-8"})
    public Object getRegisteredRecruitment(String studentId,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                   Integer pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                       Integer pageSize,
                                           @RequestParam(name = "onPage", required = false, defaultValue = "-1")
                                                       Integer onPage){
        if(onPage!=-1)
            return userService.getRegisteredRecruitment(studentId,pageNum,pageSize);
        else
            return userService.getRegisteredRecruitmentNotOnPage(studentId);
    }

    @GetMapping(value = "/tia/user/created", produces = {"application/json;charset=UTF-8"})
    public Object getCreatedRecruitment(String studentId,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                    Integer pageSize,
                                        @RequestParam(name = "onPage", required = false, defaultValue = "-1")
                                                    Integer onPage){
        if(onPage!=-1)
            return userService.getCreatedRecruitment(studentId,pageNum,pageSize);
        else
            return userService.getCreatedRecruitmentNotOnPage(studentId);
    }


}

