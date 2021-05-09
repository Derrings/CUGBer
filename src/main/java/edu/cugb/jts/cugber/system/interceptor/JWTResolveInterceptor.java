package edu.cugb.jts.cugber.system.interceptor;

import edu.cugb.jts.cugber.common.SystemStrings;
import edu.cugb.jts.cugber.pojo.dto.UserTokenInf;
import edu.cugb.jts.cugber.util.AESUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The token contains users' information, to make it safe all tokens are
 * formatted like this:
 * {"UUID":"xxx", "usrInf":{"identity":"xxx", "id":"xxx", "authority":"xxx"}}
 * the 'usrInf' was encrypt by secret key stored in redis which can be checked
 * by the only uuid.
 *
 * @author Derrings
 */
@Slf4j
@Component
public class JWTResolveInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private AESUtil aesUtil;
    @Resource
    private SecretKey secretKey;

    // Resolving token and put user information into servlet request.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request process to " + request.getRequestURL());
        String tokenStr = request.getHeader("token");
        // Request do not have a token.
        if (tokenStr == null) {
            log.debug("Request processing to " + request.getRequestURL() + " have no token.");
            return false;
        }
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(tokenStr).getBody();
        String UUID = (String) claims.get(SystemStrings.TOKEN_UUID);
        String AESSecret = (String) redisTemplate.opsForValue().get(UUID);
        // Cannot find the uuid or uuid have lost efficacy.
        if (AESSecret == null) {
            log.debug("UUID:" + UUID + " have lost its efficacy.");
            return false;
        }
        String usrInf = (String) claims.get(SystemStrings.TOKEN_USER_INFORMATION);
        UserTokenInf userTokenInf;
        if ((userTokenInf = aesUtil.AESDecode(AESSecret, usrInf, UserTokenInf.class)) != null) {
            request.setAttribute(SystemStrings.REQUEST_USER_INFORMATION, userTokenInf);
            request.setAttribute(SystemStrings.REQUEST_UUID, UUID);
            return true;
        } else {
            log.debug("UUID:" + UUID + " do not have correct key.");
            return false;
        }
    }
}