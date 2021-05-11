package edu.cugb.jts.cugber.system.interceptor;

import edu.cugb.jts.cugber.common.SystemStrings;
import edu.cugb.jts.cugber.pojo.dto.UserTokenInf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request process to " + request.getRequestURL());
        UserTokenInf userTokenInf = (UserTokenInf) request.getAttribute(SystemStrings.REQUEST_USER_INFORMATION);
        int id = userTokenInf.getId();
        String identity = userTokenInf.isAdmin() ? "admin" : "user";
        return redisTemplate.opsForValue().get(identity + id) != null;
    }
}
