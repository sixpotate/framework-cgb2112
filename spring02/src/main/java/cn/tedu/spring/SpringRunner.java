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
        UserMapper userMapper1 = ac.getBean("userMapper", UserMapper.class);
        UserMapper userMapper2 = ac.getBean("userMapper", UserMapper.class);
        UserMapper userMapper3 = ac.getBean("userMapper", UserMapper.class);
        System.out.println("2. 从Spring中获取对象，完成！");
        System.out.println();

        // 3. 测试使用对象，以便于观察是否获取到了有效的对象
        System.out.println("3. 测试使用对象，开始……");
        System.out.println("\tuserMapper1 > " + userMapper1);
        System.out.println("\tuserMapper2 > " + userMapper2);
        System.out.println("\tuserMapper3 > " + userMapper3);
        System.out.println("3. 测试使用对象，完成！");
        System.out.println();

        // 4. 关闭
        System.out.println("4. 关闭，开始……");
        ac.close();
        System.out.println("4. 关闭，完成！");
    }
}
