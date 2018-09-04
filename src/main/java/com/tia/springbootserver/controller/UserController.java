package com.tia.springbootserver.controller;


import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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



}
