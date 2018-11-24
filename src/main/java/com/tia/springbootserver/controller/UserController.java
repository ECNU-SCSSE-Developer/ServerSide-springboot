package com.tia.springbootserver.controller;


import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.mapper.UserFocusedMapper;
import com.tia.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFocusedMapper userFocusedMapper;


    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public Object getUserByIdWithSplitContacts(@RequestParam(value = "studentId") String studentId,@RequestParam(value = "isSplit",defaultValue = "1") Integer isSplit){
        if (isSplit==1)
            return userService.getByIdWithSplitContacts(studentId);
        else
            return userService.getByStudentId(studentId);
    }

    @GetMapping(value = "/oid", produces = {"application/json;charset=UTF-8"})
    public Object getUserByOpenId(String openId){
        return userService.getByOpenId(openId);
    }




    @PostMapping(produces = {"application/json;charset=UTF-8"})
    public Object createUser(User user)
    {
        return userService.insertUser(user);
    }

    @PutMapping(produces = {"application/json;charset=UTF-8"})
    public Object updateUserInfo(User user){return userService.updateUserInfo(user);}

    @GetMapping(value = "/focused", produces = {"application/json;charset=UTF-8"})
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

    @PutMapping(value = "/focused", produces = {"application/json;charset=UTF-8"})
    public Object addFocusedRecruitment(String studentId, Integer recruitId){
        if(userFocusedMapper.selectUserFocused(recruitId,studentId)==null)
            return userService.addFocusedRecruitment(studentId, recruitId);
        return 0;
    }

    @DeleteMapping(value = "/focused", produces = {"application/json;charset=UTF-8"})
    public Object deleteFocusedRecruitment(String studentId, Integer recruitId){
        return userService.deleteFocusedRecruitment(studentId, recruitId);
    }

    @GetMapping(value = "/applied", produces = {"application/json;charset=UTF-8"})
    public Object getAppliedRecruitment(String studentId){
        return userService.getAppliedRecruitmentNotOnPage(studentId);
    }


    @GetMapping(value = "/registered", produces = {"application/json;charset=UTF-8"})
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

    @GetMapping(value = "/created", produces = {"application/json;charset=UTF-8"})
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

