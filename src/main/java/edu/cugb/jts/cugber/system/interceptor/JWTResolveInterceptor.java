package edu.cugb.jts.cugber.system.interceptor;

import edu.cugb.jts.cugber.pojo.dto.UserTokenInf;
import edu.cugb.jts.cugber.util.AESUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenStr = request.getHeader("authority");
        // Do not have
        if (tokenStr == null) {
            return false;
        }
        Claims claims = Jwts.parserBuilder().setSigningKey(tokenStr).build().parseClaimsJws(tokenStr).getBody();
        String UUID = (String) claims.get("UUID");
        String AESSecret = (String) redisTemplate.opsForValue().get(UUID);
        if (AESSecret == null) {
            return false;
        }
        String usrInf = (String) claims.get("usrInf");
        UserTokenInf userTokenInf = null;
        if ((userTokenInf = aesUtil.AESDecode(AESSecret, usrInf, UserTokenInf.class)) != null) {
            request.setAttribute("userInfo", userTokenInf);
            return true;
        } else {
            return false;
        }
    }
}
