package com.tia.springbootserver.controller;

import com.tia.springbootserver.entity.SessionId;
import com.tia.springbootserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrew Dong
 * @date 2018/10/18 18:37
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * @param code
     */
    @GetMapping(value = "/tia/onLogin")
    public SessionId onLogin(@RequestParam(name = "code") String code) {
        SessionId sessionid = loginService.getWxSession(code);
        return sessionid;
    }

    @GetMapping(value = "/tia/test")
    public String test(@RequestAttribute(name = "openid") String openid) {
        return openid;
    }
}
