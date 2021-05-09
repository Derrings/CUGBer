package edu.cugb.jts.cugber.system.interceptor;

import edu.cugb.jts.cugber.common.SystemStrings;
import edu.cugb.jts.cugber.pojo.dto.UserTokenInf;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserTokenInf userTokenInf = (UserTokenInf) request.getAttribute(SystemStrings.REQUEST_USER_INFORMATION);
        int id = userTokenInf.getId();
        String identity = userTokenInf.isAdmin() ? "admin" : "user";
        return redisTemplate.opsForValue().get(identity + id) != null;
    }
}
