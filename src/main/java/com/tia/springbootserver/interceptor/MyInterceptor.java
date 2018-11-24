package com.tia.springbootserver.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Andrew Dong
 * @date 2018/10/20 19:18
 */
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 从请求头中获取sessionid,判断redis中是否存在该sessionid,若存在则放行并重置redis中存放时间,
     * 若不存在,则返回false
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionid = request.getHeader("sessionid");
        if (sessionid == null){
            logger.info("拒绝访问");
            return false;
        } else {
            String str = stringRedisTemplate.opsForValue().get(sessionid);
            String[] strarr = str.split("#");
            logger.info("openid:" + strarr[1]);//strarr[1]中存的是openid

            //将openid放入参数中
            request.setAttribute("openid", strarr[1]);
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
