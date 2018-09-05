package com.tia.springbootserver.controller;


import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // TODO():
    @RequestMapping("/user")
    public String index(){
        return "UserController is here.";
    }


    @RequestMapping(value = "/user/get", produces = {"application/json;charset=UTF-8"})
    public Object getUserById(String studentId)
    {
        return userService.getById(studentId);
    }

    @RequestMapping(value = "/user/create", produces = {"application/json;charset=UTF-8"})
    public Object createUser(User user)
    {
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/user/update", produces = {"application/json;charset=UTF-8"})
    public Object updateUserInfo(User user){return userService.updateUserInfo(user);}

    @RequestMapping(value = "/user/get/focused", produces = {"application/json;charset=UTF-8"})
    public Object getFocusedRecruitment(String studentId,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                    Integer pageSize){
        return userService.getFocusedRecruitment(studentId,pageNum,pageSize);
    }

    @RequestMapping(value = "/user/add/focused", produces = {"application/json;charset=UTF-8"})
    public Object addFocusedRecruitment(String studentId, Integer recruitId){
        return userService.addFocusedRecruitment(studentId, recruitId);
    };

    @RequestMapping(value = "/user/delete/focused", produces = {"application/json;charset=UTF-8"})
    public Object deleteFocusedRecruitment(String studentId, Integer recruitId){
        return userService.deleteFocusedRecruitment(studentId, recruitId);
    };


    @RequestMapping(value = "/user/get/registered", produces = {"application/json;charset=UTF-8"})
    public Object getRegisteredRecruitment(String studentId,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                   Integer pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                       Integer pageSize){
        return userService.getRegisteredRecruitment(studentId,pageNum,pageSize);
    }

    @RequestMapping(value = "/user/get/created", produces = {"application/json;charset=UTF-8"})
    public Object getCreatedRecruitment(String studentId,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                    Integer pageSize){
        return userService.getCreatedRecruitment(studentId,pageNum,pageSize);
    }

    @RequestMapping(value = "/user/accept", produces = {"application/json;charset=UTF-8"})
    public Object acceptUser(Integer recruitId, @RequestParam(name = "studentId") String applicantId){
        return userService.acceptUser(recruitId,applicantId);
    }
}

