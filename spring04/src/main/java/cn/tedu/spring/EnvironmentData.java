package cn.tedu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentData {

    @Autowired
    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

}
