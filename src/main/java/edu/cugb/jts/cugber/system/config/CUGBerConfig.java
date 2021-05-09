package edu.cugb.jts.cugber.system.config;

import edu.cugb.jts.cugber.system.devops.SystemFunctionStatusManager;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class CUGBerConfig {
    private static final String BLOG_URL_PREFIX = "blog";
    private static final String LOST_AND_FOUND_URL_PREFIX = "laf";
    private static final String QUEST_URL_PREFIX = "quest";
    private static final String SAY_URL_PREFIX = "say";
    private static final String GO_TOGETHER = "gt";

    @Bean
    public SystemFunctionStatusManager systemFunctionStatusMapping() {
        SystemFunctionStatusManager sfsm = new SystemFunctionStatusManager();
        sfsm.addFunctionalSection(BLOG_URL_PREFIX);
        sfsm.addFunctionalSection(LOST_AND_FOUND_URL_PREFIX);
        sfsm.addFunctionalSection(QUEST_URL_PREFIX);
        sfsm.addFunctionalSection(SAY_URL_PREFIX);
        sfsm.addFunctionalSection(GO_TOGETHER);
        return sfsm;
    }

    @Bean
    public SecretKey JWTSecretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
