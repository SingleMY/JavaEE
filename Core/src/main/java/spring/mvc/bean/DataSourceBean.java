package spring.mvc.bean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.mvc.util.DBpropertiesUtils;

import javax.sql.DataSource;

@Configuration
public class DataSourceBean {


    private static DataSource dbDS;

    private DataSourceBean() {}

    @Bean(name = "datasource")
    private static DataSource getConnectionPool() {
        if (dbDS == null) {
            dbDSInit();
        }
        return dbDS;
    }

    private static void dbDSInit() {
        HikariConfig config = new HikariConfig();

        DBpropertiesUtils properties = DBpropertiesUtils.getInstance();
        config.setJdbcUrl(properties.getProperty("jdbcUrl"));
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));
        config.setDriverClassName(properties.getProperty("driverClassName"));

        dbDS = new HikariDataSource(config);
    }
}
