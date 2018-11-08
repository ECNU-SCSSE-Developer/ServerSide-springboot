package com.tia.springbootserver.controller;


import com.tia.springbootserver.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;


    @GetMapping(value = "/match",produces = {"application/json;charset=UTF-8"})
    public Object getMatch(HttpServletRequest request)
    {
        //
        String pageNumString = request.getParameter("pageNum");
        String pageSizeString = request.getParameter("pageSize");
        Integer pageNum = pageNumString!=null ? Integer.parseInt(pageNumString) : 1 ;
        Integer pageSize = pageSizeString!=null ? Integer.parseInt(pageSizeString) : 10 ;
        //
        String selectAllString = request.getParameter("selectAll");
        //
        String matchIdString = request.getParameter("matchId");
        String matchNameString = request.getParameter("matchName");
        // is on Page
        String onPageString = request.getParameter("onPage");
        //
        if(selectAllString!=null) {
            if (onPageString!=null)
                return matchService.findAllMatch(pageNum, pageSize);
            else
                return matchService.findAllMatchNotOnPage();
        }
        else if(matchIdString!=null)
            return matchService.getMatchById(Integer.parseInt(matchIdString));
        else if(matchNameString!=null) {
            if (onPageString!=null)
                return matchService.findMatchByName(matchNameString, pageNum, pageSize);
            else
                return matchService.findMatchByNameNotOnPage(matchNameString);
        }
        else
            return matchService.getMatchById(-1);
    }


}
