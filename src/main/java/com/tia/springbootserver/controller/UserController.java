package com.tia.springbootserver.controller;


import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public Object getUserById(String studentId)
    {
        return userService.getById(studentId);
    }

    @PostMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public Object createUser(User user)
    {
        return userService.insertUser(user);
    }

    @PutMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public Object updateUserInfo(User user){return userService.updateUserInfo(user);}

    @GetMapping(value = "/user/focused", produces = {"application/json;charset=UTF-8"})
    public Object getFocusedRecruitment(String studentId,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                    Integer pageSize){
        return userService.getFocusedRecruitment(studentId,pageNum,pageSize);
    }

    @PostMapping(value = "/user/focused", produces = {"application/json;charset=UTF-8"})
    public Object addFocusedRecruitment(String studentId, Integer recruitId){
        return userService.addFocusedRecruitment(studentId, recruitId);
    };

    @DeleteMapping(value = "/user/focused", produces = {"application/json;charset=UTF-8"})
    public Object deleteFocusedRecruitment(String studentId, Integer recruitId){
        return userService.deleteFocusedRecruitment(studentId, recruitId);
    };


    @GetMapping(value = "/user/registered", produces = {"application/json;charset=UTF-8"})
    public Object getRegisteredRecruitment(String studentId,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                   Integer pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                       Integer pageSize){
        return userService.getRegisteredRecruitment(studentId,pageNum,pageSize);
    }

    @GetMapping(value = "/user/created", produces = {"application/json;charset=UTF-8"})
    public Object getCreatedRecruitment(String studentId,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                Integer pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                    Integer pageSize){
        return userService.getCreatedRecruitment(studentId,pageNum,pageSize);
    }

    @PutMapping(value = "/user/accept", produces = {"application/json;charset=UTF-8"})
    public Object acceptUser(Integer recruitId, @RequestParam(name = "studentId") String applicantId){
        return userService.acceptUser(recruitId,applicantId);
    }
}

