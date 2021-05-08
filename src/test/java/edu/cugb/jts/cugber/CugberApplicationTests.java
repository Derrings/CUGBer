package edu.cugb.jts.cugber;

import edu.cugb.jts.cugber.common.SystemTimeLimit;
import edu.cugb.jts.cugber.pojo.dao.User;
import edu.cugb.jts.cugber.util.TimeUtil;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
    RedisTemplate<String, Object> redisTemplate;

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
    void redisTemplateTest() {
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("zhaoshuai", new User("zhaoshuai", "男", true));
        System.out.println(opsForValue.get("zhaoshuai"));
    }

    @Test
    void utilsTest() {
        System.out.println(Integer.MAX_VALUE);
        new SystemTimeLimit.SysTime(30, TimeUnit.HOURS).calculateMilliSeconds();
        TimeUtil.nowToMidNight();
    }

}
