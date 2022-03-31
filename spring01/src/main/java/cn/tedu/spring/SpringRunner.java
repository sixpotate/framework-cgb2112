package cn.tedu.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class SpringRunner {

    public static void main(String[] args) {
        // 1. 加载Spring
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(SpringBeanFactory.class);

        // 2. 从Spring中获取对象
        Random random = (Random) ac.getBean("random");

        // 3. 测试使用对象，以便于观察是否获取到了有效的对象
        System.out.println("random > " + random);
        System.out.println("random.nextInt() > " + random.nextInt());

        // 4. 关闭
        ac.close();
    }

}
