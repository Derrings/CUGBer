package edu.cugb.jts.cugber.system.config;

import edu.cugb.jts.cugber.system.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] ALL_PERMIT_URLS = {
            "login",
            "admin-login"
    };

    @Resource
    private JWTResolveInterceptor jwtResolveInterceptor;
    @Resource
    private AuthorityInterceptor authorityInterceptor;
    @Resource
    private IdentityInterceptor identityInterceptor;
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private SystemInterceptor systemInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtResolveInterceptor)
                .excludePathPatterns(ALL_PERMIT_URLS);
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(ALL_PERMIT_URLS);
        registry.addInterceptor(identityInterceptor)
                .excludePathPatterns(ALL_PERMIT_URLS);
        registry.addInterceptor(authorityInterceptor)
                .excludePathPatterns(ALL_PERMIT_URLS);
        registry.addInterceptor(systemInterceptor)
                .excludePathPatterns(ALL_PERMIT_URLS);
    }
}
