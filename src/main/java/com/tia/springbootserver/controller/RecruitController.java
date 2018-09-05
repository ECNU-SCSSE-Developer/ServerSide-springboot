package com.tia.springbootserver.controller;

import com.tia.springbootserver.entity.MatchRecruit;
import com.tia.springbootserver.entity.RecruitApplicants;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.UserCreated;
import com.tia.springbootserver.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @RequestMapping(value = "/recruit/create", produces = {"application/json;charset=UTF-8"})
    public Object createRecruit(Recruitment recruitment, MatchRecruit matchRecruit, UserCreated userCreated){
        recruitment.setRegisteredNumber(0);
        if (recruitment.getRecruitId()!=null) {
            recruitService.bindToMatch(matchRecruit);
            recruitService.createRecruitWithId(recruitment);
            return recruitment.getRecruitId();
        }
        else {
            //TODO: 异常回滚
            recruitService.createRecruit(recruitment);
            int recruitId = recruitment.getRecruitId();
            matchRecruit.setRecruitId(recruitId);
            userCreated.setRecruitId(recruitId);
            recruitService.bindToMatch(matchRecruit);
            recruitService.bindToUser(userCreated);
            return recruitId;
        }

    }

    @RequestMapping(value = "/recruit/update", produces = {"application/json;charset=UTF-8"})
    public Object updateRecruitInfo(Recruitment recruitment){
        return recruitService.updateRecruitInfo(recruitment);
    }

    @RequestMapping(value = "/recruit/get/id", produces = {"application/json;charset=UTF-8"})
    public Object getRecruitById(Integer recruitId){
        return recruitService.findRecruitById(recruitId);
    }

    @RequestMapping(value = "/recruit/get/name", produces = {"application/json;charset=UTF-8"})
    public Object getRecruitByName(String recruitName,
                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                           Integer pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                               Integer pageSize){
        return recruitService.findRecruitByName(recruitName,pageNum,pageSize);
    }

    @RequestMapping(value = "/recruit/delete", produces = {"application/json;charset=UTF-8"})
    public Object deleteRecruit(Integer recruitId){
        recruitService.unBindFromMatch(recruitId);
        recruitService.deleteRecruitFromUser(recruitId);
        return recruitService.deleteRecruit(recruitId);
    }

    //申请
    @RequestMapping(value = "/recruit/register", produces = {"application/json;charset=UTF-8"})
    public Object registerRecruit(RecruitApplicants recruitApplicants){
        return recruitService.register(recruitApplicants);
    }

    @RequestMapping(value = "/recruit/unregister", produces = {"application/json;charset=UTF-8"})
    public Object unregisterRecruit(RecruitApplicants recruitApplicants){
        return recruitService.unregister(recruitApplicants);
    }

    @RequestMapping(value = "/recruit/get/bind-match", produces = {"application/json;charset=UTF-8"})
    public Object getBindMatch(Integer recruitId){
        return recruitService.getBindMatch(recruitId);
    }

    @RequestMapping(value = "/recruit/get/applicants", produces = {"application/json;charset=UTF-8"})
    public Object getApplicantsInfo(Integer recruitId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                            Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                Integer pageSize){
        return recruitService.getApplicantsInfo(recruitId,pageNum,pageSize);
    }

}
