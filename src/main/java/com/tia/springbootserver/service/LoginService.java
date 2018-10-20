package com.tia.springbootserver.service;

import com.tia.springbootserver.entity.SessionId;

/**
 * @author Andrew Dong
 * @date 2018/10/18 18:45
 */
public interface LoginService {
    String create3rdSession(String wxOpenId, String wxSessionKey);//生成第三方session
    SessionId getWxSession(String code);
}
