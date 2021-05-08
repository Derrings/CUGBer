package edu.cugb.jts.cugber;

import edu.cugb.jts.cugber.pojo.dao.User;
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

@SpringBootTest
class CugberApplicationTests {
    @Resource
    DataSource dataSource;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    // 测试数据源是否有效
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

}
