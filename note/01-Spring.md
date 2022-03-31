# Spring Framework

## 1. 关于Spring

Spring框架主要解决了创建对象、管理对象的问题。

在传统的开发中，当需要某个对象时，使用`new`关键字及类型的构造方法即可创建对象，例如：

```java
Random random = new Random();
```

如果以上代码存在于某个方法中。则`random`就只是个局部变量，当方法运行结束，此变量就会被销毁！

在实际项目开发，许多对象被创建出来之后，应该长期存在于内存中，而不应该销毁，当需要使用这些对象时，通过某种方式获取对象即可，而不应该重新创建对象！

除了对象存在的时间（时长）以外，在实际项目开发中，还需要关注各个类型之间的依赖关系！例如：

```java
public class UserMapper {
    
    public void insert() {
        // 向数据表中的“用户表”中插入数据
    }
    
}
```

```java
public class UserController {
    
    public UserMapper userMapper;
    
    public void reg() {
        userMapper.insert();
    }
    
}
```

在以上示例代码中，可视为“`UserController`是依赖于`UserMapper`的”，也可以把`UserMapper`称之为“`UserController`的依赖项”。

当需要创建以上类型时，如果只是单纯的把`UserController`创建出来了，却没有关注其内部`userMapper`属性的值，甚至该属性没有值，则是错误的做法！

如果要使得`UserController`中的`UserMapper`属性是有值的，也非常简单，例如：

```java
public class UserController {
    
    public UserMapper userMapper = new UserMapper();
    
    public void reg() {
        userMapper.insert();
    }
    
}
```

但是，在实际项目中，除了`UserController`以外，还会有其它的组件也可能需要使用到`UserMapper`，如果每个组件中都使用`new UserMapper()`的语法来创建对象，就不能保证`UserMapper`对象的唯一性，就违背了设计初衷。

为了更好的创建对象和管理对象，应该使用Spring框架！

## 2. 创建基于Spring的工程

当某个项目需要使用Spring框架时，推荐使用Maven工程。

创建名为`spring01`的Maven工程，创建完成后，会自动打开`pom.xml`文件，首先，应该在此文件中添加`<dependencies>`节点，此节点是用于添加依赖项的：

```xml
<dependencies>

</dependencies>
```

然后，Spring框架的依赖项的代码需要编写在以上节点的子级，而依赖项的代码推荐从 https://mvnrepository.com/ 网站查询得到，Spring的依赖项名称是`spring-context`，则在此网站搜索该名称，以找到依赖项的代码，代码示例：

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.14</version>
</dependency>
```

将以上依赖项的代码复制到`<dependencies>`节点之下即可。

然后，点击IDEA中悬浮的刷新Maven的图标，或展开右侧的Maven面板点击刷新按钮，即可自动下载所需要的jar包文件（这些文件会被下载到本机的Maven仓库中，同一个依赖项的同一个版本只会下载一次）。

## 3. 通过Spring创建对象--通过@Bean方法

**演示案例：spring01**

操作步骤：

- 创建`cn.tedu.spring.SpringBeanFactory`类
- 在类中添加方法，方法的返回值类型就是你希望Spring创建并管理的对象的类型，并在此方法中自行编写返回有效对象的代码
- 在此类上添加`@Configuration`注解
- 在此方法上添加`@Bean`注解

以上步骤的示例代码：

```java
package cn.tedu.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class SpringBeanFactory {

    @Bean
    public Random random() {
        return new Random();
    }

}
```

接下来，创建某个类用于执行：

```java
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
```

接下来，运行`SpringRunner`类的`main()`方法即可看到执行效果。

关于以上代码：

- 在`AnnotationConfigApplicationContext`的构造方法中，应该将`SpringBeanFactory.class`作为参数传入，否则就不会加载`SpringBeanFactory`类中内容
  - 其实，在以上案例中，`SpringBeanFactory`类上的`@Configuration`注解并不是必须的
- 在`getBean()`时，传入的字符串参数`"random"`是`SpringBeanFactory`类中的方法的名称
- 在`SpringBeanFactory`类中的方法必须添加`@Bean`注解，其作用是使得Spring框架自动调用此方法，并管理此方法返回的结果
- 关于`getBean()`方法，此方法被重载了多次，典型的有：
  - `Object getBean(String beanName)`
    - 通过此方法，传入的`beanName`必须是有效的，否则将导致`NoSuchBeanDefinitionException`
  - `T getBean(Class<T> beanClass)`;
    - 使用此方法时，传入的类型在Spring中必须**有且仅有1个对象**，如果没有匹配类型的对象，将导致`NoSuchBeanDefinitionException`，如果有2个，将导致`NoUniqueBeanDefinitionException`
  - `T getBean(String beanName, Class<T> beanClass)`
    - 此方法仍是根据传入的`beanName`获取对象，并且根据传入的`beanClass`进行类型转换
- 使用的`@Bean`注解可以传入`String`类型的参数，如果传入，则此注解对应的方法的返回结果的`beanName`就是`@Bean`注解中传入的`String`参数值

## 4. 通过Spring创建对象--

**演示案例：spring02**

先使用同样的步骤创建`spring02`工程。

操作步骤：

- 在`pom.xml`中添加`spring-context`的依赖项

- 自行创建某个类，例如创建`cn.tedu.spring.UserMapper`类，并在类的声明之前添加`@Component`注解

- 与前次案例相似，创建可执行的类，与前次案例的区别在于：

  - 在`AnnotationConfigApplicationContext`的构造方法中传入的是`UserMapper`类的包名，即：

    ```java
    AnnotationConfigApplicationContext ac
        	= new AnnotationConfigApplicationContext("cn.tedu.spring");
    ```

  - 调用`getBean()`时，传入的名称是将`UserMapper`类的名称的首字母改为小写，即：

    ```java
    UserMapper userMapper = ac.getBean("userMapper", UserMapper.class);
    ```

    





