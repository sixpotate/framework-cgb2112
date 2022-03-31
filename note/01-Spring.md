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

## 3. 通过Spring创建对象

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

​		













