package cn.tedu.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class SpringRunner {

    public static void main(String[] args) {
        // 1. 加载Spring
        System.out.println("1. 加载Spring，开始……");
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println("1. 加载Spring，完成！");
        System.out.println();

        // 2. 从Spring中获取对象
        System.out.println("2. 从Spring中获取对象，开始……");
        JdbcConfig jdbcConfig
                = ac.getBean("jdbcConfig", JdbcConfig.class);
        EnvironmentData environmentData
                = ac.getBean("environmentData", EnvironmentData.class);
        System.out.println("2. 从Spring中获取对象，完成！");
        System.out.println();

        // 3. 测试使用对象，以便于观察是否获取到了有效的对象
        System.out.println("3. 测试使用对象，开始……");
        System.out.println("通过@Value注解获取的值：");
        System.out.println("\turl >> " + jdbcConfig.getUrl());
        System.out.println("\tdriver >> " + jdbcConfig.getDriver());
        System.out.println("\tusername >> " + jdbcConfig.getUsername());
        System.out.println("\tpassword >> " + jdbcConfig.getPassword());
        System.out.println("\tinit-size >> " + jdbcConfig.getInitSize());
        System.out.println("\tmax-active >> " + jdbcConfig.getMaxActive());
        System.out.println("---------------------------------------");
        System.out.println("通过自动装配Environment对象获取的值：");
        Environment env = environmentData.getEnvironment();
        System.out.println("\tEnvironment >> " + env);
        System.out.println("\turl >> " + env.getProperty("spring.jdbc.url"));
        System.out.println("\tdriver >> " + env.getProperty("spring.jdbc.driver"));
        System.out.println("\tusername >> " + env.getProperty("spring.jdbc.username"));
        System.out.println("\tpassword >> " + env.getProperty("spring.jdbc.password"));
        System.out.println("\tinit-size >> " + env.getProperty("spring.jdbc.init-size"));
        System.out.println("\tmax-active >> " + env.getProperty("spring.jdbc.max-active"));
        System.out.println("3. 测试使用对象，完成！");
        System.out.println();

        // 4. 关闭
        System.out.println("4. 关闭，开始……");
        ac.close();
        System.out.println("4. 关闭，完成！");
    }
}
