package test.epam.esm.config;

import com.epam.esm.pool.GiftDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"com.epam.esm"})
@PropertySource("classpath:testdb.properties")
public class SpringTestConfig {


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


}
