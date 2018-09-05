package com.tia.springbootserver.controller;


import com.tia.springbootserver.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/match/get/all", produces = {"application/json;charset=UTF-8"})
    public Object getAllMatchOnPage(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    Integer pageSize){
        return matchService.findAllMatch(pageNum,pageSize);
    }

    @RequestMapping(value = "/match/get/id", produces = {"application/json;charset=UTF-8"})
    public Object getMatchById(Integer matchId){
        return matchService.getMatchById(matchId);
    }

    @RequestMapping(value = "/match/get/name", produces = {"application/json;charset=UTF-8"})
    public Object getMatchByName(String matchName,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                         Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                                             Integer pageSize){
        return matchService.findMatchByName(matchName,pageNum,pageSize);
    }
}
