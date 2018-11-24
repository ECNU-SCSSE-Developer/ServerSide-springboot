package com.tia.springbootserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tia.springbootserver.entity.SessionId;
import com.tia.springbootserver.entity.User;
import com.tia.springbootserver.mapper.UserMapper;
import com.tia.springbootserver.service.LoginService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * @author Andrew Dong
 * @date 2018/10/18 18:59
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserMapper userMapper;

    /**
     * 生成自定义第三方session,并存入redis中
     * @param wxOpenId
     * @param wxSessionKey
     * @return
     */
    @Override
    public String create3rdSession(String wxOpenId, String wxSessionKey) {
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);//随机数生成
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);

        //缓存到Redis,存放时间应比微信小程序登录态维持时间久
        stringRedisTemplate.opsForValue().set(thirdSessionKey, sb.toString(), 100, TimeUnit.DAYS);
        return thirdSessionKey;
    }

    /**
     * 调取微信服务器接口，生成加密服务器密钥
     * @param code
     * @return
     */
    @Override
    public SessionId getWxSession(String code) {

        RestTemplate restTemplate = new RestTemplate();

        //覆盖RestTemplate默认的异常处理方法
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        };
        restTemplate.setErrorHandler(responseErrorHandler);

        LinkedMultiValueMap body = new LinkedMultiValueMap();
        body.add("grant_type", "authorization_code");
        body.add("appid", "wx0eb8aa287ec3013e");//小程序的addid
        body.add("secret", "978a121b32983e84be1cb134b0c3659d");//小程序的secret
        body.add("js_code", code);

        String url="https://api.weixin.qq.com/sns/jscode2session";
        HttpHeaders headers = new HttpHeaders();//这个对象有add()方法，可往请求头存入信息
        HttpEntity entity = new HttpEntity(body, headers);

        ResponseEntity<String> res;
        res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);//返回的结果信息,String.class是可以修改的，取决于怎么解析请求返回的参数

        //logger.info("res statusCode:" + res.getStatusCode());
        if (Integer.parseInt(String.valueOf(res.getStatusCode())) == 200){
            //logger.info("res body:" + res.getBody());
            JSONObject jsonObject = JSONObject.parseObject(res.getBody());
            String sessionKey = jsonObject.getString("session_key");
            String openid = jsonObject.getString("openid");
            User user = new User();
            user.setOpenid(openid);
            userMapper.insertSelective(user);
            String sId = create3rdSession(openid, sessionKey);
            SessionId sessionId = new SessionId();
            sessionId.setSessionid(sId);
            return sessionId;
        } else {
            return null;
        }
    }
}
