package edu.cugb.jts.cugber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cugb.jts.cugber.common.SystemTimeLimit;
import edu.cugb.jts.cugber.pojo.dto.UserTokenInf;
import edu.cugb.jts.cugber.util.AESUtil;
import edu.cugb.jts.cugber.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CugberApplicationTests {
    @Resource
    DataSource dataSource;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    AESUtil aesUtil;

    // To test whether data source is effective.
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from usr");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(3));
        }
    }

    @Test
    void redisTest() {
        redisTemplate.opsForValue().set(12 + "", new UserTokenInf());
        System.out.println(redisTemplate.opsForValue().get(13 + ""));
        System.out.println(redisTemplate.opsForValue().get(12 + ""));
    }
    @Test
    void utilsTest() {
        System.out.println(Integer.MAX_VALUE);
        new SystemTimeLimit.SysTime(30, TimeUnit.HOURS).calculateMilliSeconds();
        TimeUtil.nowToMidNight();
    }

    @Test
    void AESEncoder() {
        String secret = aesUtil.AESEncode("asdf", new UserTokenInf());
        aesUtil.AESDecode("klk", secret, UserTokenInf.class);
    }

}
