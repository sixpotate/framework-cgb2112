package cn.tedu.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JdbcConfig {

    @Value("${spring.jdbc.url}")
    private String url;
    @Value("${spring.jdbc.driver}")
    private String driver;
    @Value("${spring.jdbc.username}")
    private String username;
    @Value("${spring.jdbc.password}")
    private String password;
    @Value("${spring.jdbc.init-size}")
    private int initSize;
    @Value("${spring.jdbc.max-active}")
    private int maxActive;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
}
