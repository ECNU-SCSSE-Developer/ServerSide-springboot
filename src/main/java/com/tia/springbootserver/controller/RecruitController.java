package com.tia.springbootserver.controller;

import com.tia.springbootserver.entity.MatchRecruit;
import com.tia.springbootserver.entity.RecruitApplicants;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.UserCreated;
import com.tia.springbootserver.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @PostMapping(value = "/recruit", produces = {"application/json;charset=UTF-8"})
    @Transactional
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


    @GetMapping(value = "/recruit", produces = {"application/json;charset=UTF-8"})
    public Object getRecruit(HttpServletRequest request){
        String recruitIdString = request.getParameter("recruitId");
        String recruitNameString = request.getParameter("recruitName");
        //
        String pageNumString = request.getParameter("pageNum");
        String pageSizeString = request.getParameter("pageSize");
        Integer pageNum = pageNumString!=null ? Integer.parseInt(pageNumString) : 1 ;
        Integer pageSize = pageSizeString!=null ? Integer.parseInt(pageSizeString) : 10 ;
        //
        if (recruitIdString.length()!=0) {
            return recruitService.findRecruitById(Integer.parseInt(recruitIdString));
        }
        else if(recruitNameString.length()!=0) {
            return recruitService.findRecruitByName(recruitNameString,pageNum,pageSize);
        }
        else
        {
            return recruitService.findRecruitById(0);
        }
    }


    @PutMapping(value = "/recruit", produces = {"application/json;charset=UTF-8"})
    public Object updateRecruitInfo(Recruitment recruitment){
        return recruitService.updateRecruitInfo(recruitment);
    }

//    @GetMapping(value = "/recruit/by-id", produces = {"application/json;charset=UTF-8"})
//    public Object getRecruitById(Integer recruitId){
//        return recruitService.findRecruitById(recruitId);
//    }
//
//    @GetMapping(value = "/recruit/by-name", produces = {"application/json;charset=UTF-8"})
//    public Object getRecruitByName(String recruitName,
//                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1")
//                                           Integer pageNum,
//                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10")
//                                               Integer pageSize){
//        return recruitService.findRecruitByName(recruitName,pageNum,pageSize);
//    }


    @GetMapping(value = "/all-recruit", produces = {"application/json;charset=UTF-8"})
    public Object getAllRecruit(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                           Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                           Integer pageSize){
        return recruitService.findAllRecruit(pageNum,pageSize);
    }


    @DeleteMapping(value = "/recruit", produces = {"application/json;charset=UTF-8"})
    public Object deleteRecruit(Integer recruitId){
        recruitService.unBindFromMatch(recruitId);
        recruitService.deleteRecruitFromUser(recruitId);
        return recruitService.deleteRecruit(recruitId);
    }

    //申请
    @PostMapping(value = "/applicant", produces = {"application/json;charset=UTF-8"})
    public Object registerRecruit(RecruitApplicants recruitApplicants){
        return recruitService.register(recruitApplicants);
    }

    @DeleteMapping(value = "/applicant", produces = {"application/json;charset=UTF-8"})
    public Object unregisterRecruit(RecruitApplicants recruitApplicants){
        return recruitService.unregister(recruitApplicants);
    }

    @GetMapping(value = "/recruit/bind-match", produces = {"application/json;charset=UTF-8"})
    public Object getBindMatch(Integer recruitId){
        return recruitService.getBindMatch(recruitId);
    }

    @GetMapping(value = "/applicant", produces = {"application/json;charset=UTF-8"})
    public Object getApplicantsInfo(Integer recruitId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                            Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                Integer pageSize){
        return recruitService.getApplicantsInfo(recruitId,pageNum,pageSize);
    }

}
