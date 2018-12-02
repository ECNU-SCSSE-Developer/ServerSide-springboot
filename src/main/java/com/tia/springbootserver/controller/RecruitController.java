package com.tia.springbootserver.controller;

import com.tia.springbootserver.entity.MatchRecruit;
import com.tia.springbootserver.entity.RecruitApplicants;
import com.tia.springbootserver.entity.RecruitType;
import com.tia.springbootserver.entity.Recruitment;
import com.tia.springbootserver.entity.returnType.RecruitStudent;
import com.tia.springbootserver.interceptor.MyInterceptor;
import com.tia.springbootserver.mapper.UserRegisteredMapper;
import com.tia.springbootserver.service.MatchService;
import com.tia.springbootserver.service.RecruitService;
import com.tia.springbootserver.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RecruitController {

    @Autowired
    private RecruitService recruitService;
    @Autowired
    private UserService userService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private UserRegisteredMapper userRegisteredMapper;


    private static final Logger logger = LoggerFactory.getLogger(RecruitController.class);


    @GetMapping(value = "/tia/allType")
    public Object findAllRecruitType(){
        return recruitService.findAllRecruitType();
    }

    @GetMapping(value = "/tia/recruit/match")
    public Object findRecruitByMatch(String matchName){
        return recruitService.findRecruitByMatchId(matchName);
    }






    @GetMapping(value = "/tia/typedRecruit", produces = {"application/json;charset=UTF-8"})
    public Object findRecruitByType(String recruitType){
        return recruitService.findRecruitByType(recruitType);
    }

    @PostMapping(value = "/tia/typedRecruit", produces = {"application/json;charset=UTF-8"})
    public Object addTypeForRecruit(@RequestBody RecruitType record){
        return recruitService.addTypeForRecruit(record);
    }

    @DeleteMapping(value = "/tia/typedRecruit", produces = {"application/json;charset=UTF-8"})
    public Object deleteTypeForRecruit(@RequestBody RecruitType record){
        if (record.getRecruitType()==null)
            record.setRecruitType("%");
        return recruitService.deleteTypeForRecruit(record);
    }


    @Transactional
    @PostMapping(value = "/tia/recruit", produces = {"application/json;charset=UTF-8"})
    public Object createRecruit(@RequestBody Recruitment recruitment){
        recruitment.setMatchName(matchService.getMatchById(recruitment.getMatchId()).getMatchName());
        return recruitService.createRecruit(recruitment);
    }


    @GetMapping(value = "/tia/recruit", produces = {"application/json;charset=UTF-8"})
    public Object getRecruit(HttpServletRequest request){
        String recruitIdString = request.getParameter("recruitId");
        String recruitNameString = request.getParameter("recruitName");
        //
        String pageNumString = request.getParameter("pageNum");
        String pageSizeString = request.getParameter("pageSize");
        Integer pageNum = pageNumString!=null ? Integer.parseInt(pageNumString) : 1 ;
        Integer pageSize = pageSizeString!=null ? Integer.parseInt(pageSizeString) : 10 ;
        //
        String selectAllString = request.getParameter("selectAll");
        String onPageString = request.getParameter("onPage");
        //
        if(selectAllString!=null) {
            if (onPageString!=null)
                return recruitService.findAllRecruit(pageNum, pageSize);
            else
                return recruitService.findAllRecruitNotOnPage();
        }
        else if (recruitIdString!=null)
            return recruitService.findRecruitById(Integer.parseInt(recruitIdString));
        else if(recruitNameString!=null) {
            if (onPageString!=null)
                return recruitService.findRecruitByName(recruitNameString, pageNum, pageSize);
            else
                return recruitService.findRecruitByNameNotOnPage(recruitNameString);
        }
        else
            return recruitService.findRecruitById(-1);
    }



    @PutMapping(value = "/tia/recruit", produces = {"application/json;charset=UTF-8"})
    public Object updateRecruitInfo(@RequestBody Recruitment recruitment){
        // 防止match的不一致
        recruitment.setMatchId(null);
        recruitment.setMatchName(null);
        return recruitService.updateRecruitInfo(recruitment);
    }


    // 和User的联系集都会被删除
    @DeleteMapping(value = "/tia/recruit", produces = {"application/json;charset=UTF-8"})
    public Object deleteRecruit(@RequestBody Integer recruitId){
        recruitService.deleteRecruitFromUser(recruitId);
        return recruitService.deleteRecruit(recruitId);
    }

    @GetMapping(value = "/tia/recruit/bind-match", produces = {"application/json;charset=UTF-8"})
    public Object getBindMatch(Integer recruitId){
        return recruitService.getBindMatch(recruitId);
    }


    @PutMapping(value = "/tia/recruit/bind-match", produces = {"application/json;charset=UTF-8"})
    public Object updateBindMatch(@RequestBody MatchRecruit matchRecruit)
    {
        String matchName = matchService.getMatchById(matchRecruit.getMatchId()).getMatchName();
        return recruitService.bindToMatch(matchRecruit.getRecruitId(),matchRecruit.getMatchId(),matchName);
    }


    //TODO: register和apply的含义以这一层为准 我懵逼
    //申请加入一个Recruit并关注
    @PutMapping(value = "/tia/applicant", produces = {"application/json;charset=UTF-8"})
    public Object applyRecruit(@RequestBody RecruitStudent recruitStudent){
        RecruitApplicants recruitApplicants = new RecruitApplicants();
        recruitApplicants.setRecruitId(recruitStudent.getRecruitId());
        recruitApplicants.setApplicantId(recruitStudent.getStudentId());
        return recruitService.register(recruitApplicants);
    }

    //取消申请一个Recruit 但不会取消关注
    @DeleteMapping(value = "/tia/applicant", produces = {"application/json;charset=UTF-8"})
    public Object cancelApplyRecruit(@RequestBody RecruitStudent recruitStudent){
        RecruitApplicants recruitApplicants = new RecruitApplicants();
        recruitApplicants.setRecruitId(recruitStudent.getRecruitId());
        recruitApplicants.setApplicantId(recruitStudent.getStudentId());
        return recruitService.unregister(recruitApplicants);
    }


    @GetMapping(value = "/tia/applicant", produces = {"application/json;charset=UTF-8"})
    public Object getApplicantsInfo(Integer recruitId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                            Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                                Integer pageSize,
                                    @RequestParam(name = "onPage", required = false, defaultValue = "-1")
                                    Integer onPage){
        if (onPage!=-1)
            return recruitService.getApplicantsInfo(recruitId,pageNum,pageSize);
        else
            return recruitService.getApplicantsInfoNotOnPage(recruitId);
    }

    //通过申请
    @PutMapping(value = "/tia/registered", produces = {"application/json;charset=UTF-8"})
    public Object acceptUser(@RequestBody RecruitApplicants recruitApplicants){
        //
        if (userRegisteredMapper.selectUserRegistered(recruitApplicants.getApplicantId(),recruitApplicants.getRecruitId())==null) {
            Recruitment recruitment = recruitService.findRecruitById(recruitApplicants.getRecruitId());
            recruitment.setRegisteredNumber(recruitment.getRegisteredNumber()+1);
            recruitService.updateRecruitInfo(recruitment);
            return userService.acceptUser(recruitApplicants.getRecruitId(),recruitApplicants.getApplicantId());
        }
        return 0;
        //
    }

    @DeleteMapping(value = "/tia/registered", produces = {"application/json;charset=UTF-8"})
    public Object cancelAcceptUser(@RequestBody RecruitApplicants recruitApplicants)
    {
        return userService.cancelAcceptUser(recruitApplicants.getRecruitId(),recruitApplicants.getApplicantId());
    }

}
