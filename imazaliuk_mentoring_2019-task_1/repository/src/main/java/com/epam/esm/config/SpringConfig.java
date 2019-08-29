package com.epam.esm.config;

import com.epam.esm.pool.GiftDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan({"com.epam.esm"})
@PropertySource("classpath:db.properties")
public class SpringConfig {


    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        GiftDataSource dataSource = new GiftDataSource();
        dataSource.setUrl(url);
        dataSource.setUserName(username);
        dataSource.setPassword(password);
        dataSource.init();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public LocalDateFormatter localDateFormatter() {
        return new LocalDateFormatter();
    }


}
