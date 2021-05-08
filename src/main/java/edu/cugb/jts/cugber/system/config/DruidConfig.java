package edu.cugb.jts.cugber.system.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Bean
    public DataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
