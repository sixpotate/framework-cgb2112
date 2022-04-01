package cn.tedu.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        JdbcConfig jdbcConfig = ac.getBean("jdbcConfig", JdbcConfig.class);
        System.out.println("2. 从Spring中获取对象，完成！");
        System.out.println();

        // 3. 测试使用对象，以便于观察是否获取到了有效的对象
        System.out.println("3. 测试使用对象，开始……");
        System.out.println("\turl >> " + jdbcConfig.getUrl());
        System.out.println("\tdriver >> " + jdbcConfig.getDriver());
        System.out.println("\tusername >> " + jdbcConfig.getUsername());
        System.out.println("\tpassword >> " + jdbcConfig.getPassword());
        System.out.println("\tinit-size >> " + jdbcConfig.getInitSize());
        System.out.println("\tmax-active >> " + jdbcConfig.getMaxActive());
        System.out.println("3. 测试使用对象，完成！");
        System.out.println();

        // 4. 关闭
        System.out.println("4. 关闭，开始……");
        ac.close();
        System.out.println("4. 关闭，完成！");
    }
}
