package edu.cugb.jts.cugber.system.interceptor;

import edu.cugb.jts.cugber.common.SystemStrings;
import edu.cugb.jts.cugber.pojo.dto.UserTokenInf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AdminIdentityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug("Request process to " + request.getRequestURL());
        UserTokenInf userTokenInf = (UserTokenInf) request.getAttribute(SystemStrings.TOKEN_USER_INFORMATION);
        if (!userTokenInf.isAdmin()) {
            log.info("The normal user whose id is: " + userTokenInf.getId() + " is trying to hack administrative system.");
            return false;
        }
        return true;
    }
}
