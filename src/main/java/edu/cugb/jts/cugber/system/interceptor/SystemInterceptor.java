package edu.cugb.jts.cugber.system.interceptor;

import edu.cugb.jts.cugber.system.devops.SystemFunctionStatusManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SystemInterceptor implements HandlerInterceptor {

    @Resource
    SystemFunctionStatusManager sfsm;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request process to " + request.getRequestURL());
        String url = request.getRequestURL().toString();
        return sfsm.checkUrlAccess(url);
    }
}
