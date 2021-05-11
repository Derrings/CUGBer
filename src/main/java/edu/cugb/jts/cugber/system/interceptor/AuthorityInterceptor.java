package edu.cugb.jts.cugber.system.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The third interceptor which was used to check the users' authority.
 * Because of users have two different identity —— administrator and
 * normal user, we have to do this job in two different way.
 *
 * @author Derrings
 */
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Request process to " + request.getRequestURL());

        return true;
    }
}
