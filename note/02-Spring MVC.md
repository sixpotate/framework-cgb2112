# Spring MVC

## 1. 关于Spring MVC

Spring MVC是基于Spring框架基础之上的，主要解决了后端服务器接收客户端提交的请求，并给予响应的相关问题。

MVC = Model + View + Controller，它们分别是：

- Model：数据模型，通常由业务逻辑层（Service Layer）和数据访问层（Data Access Object Layer）共同构成
- View：视图
- Controller：控制器

MVC为项目中代码的职责划分提供了参考。

需要注意：Spring MVC框架只关心V - C之间的交互，与M其实没有任何关系。

## 2. 创建Spring MVC工程

请参考 http://doc.canglaoshi.org/doc/idea_tomcat/index.html 创建项目，首次练习的项目名称请使用`springmvc01`。

## 3. 使用Spring MVC工程接收客户端的请求

**【操作步骤】**

- 在`pom.xml`中添加`spring-webmvc`依赖项：

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.14</version>
  </dependency>
  ```

  提示：如果后续运行时提示不可识别Servlet相关类，则补充添加以下依赖项：

  ```xml
  <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
  <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
  </dependency>
  ```

- 接下来，准备2个配置类，一个是Spring框架的配置类，一个是Spring MVC框架的配置类：

  **cn.tedu.springmvc.config.SpringConfig.java**

  ```java
  package cn.tedu.springmvc.config;
  
  import org.springframework.context.annotation.Configuration;
  
  @Configuration // 此注解不是必须的
  public class SpringConfig {
  }
  ```

  **cn.tedu.springmvc.config.SpringMvcConfig.java**

  ```java
  package cn.tedu.springmvc.config;
  
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
  
  @Configuration // 此注解不是必须的
  @ComponentScan("cn.tedu.springmvc") // 必须配置在当前配置类，不可配置在Spring的配置类
  public class SpringMvcConfig implements WebMvcConfigurer {
  }
  ```

- 接下来，需要创建项目的初始化类，此类必须继承自`AbstractAnnotationConfigDispatcherServletInitializer`，并在此类中重写父类的3个抽象方法，返回正确的值（各方法的意义请参见以下代码中的注释）：

  **cn.tedu.springmvc.SpringMvcInitializer**

  ```java
  package cn.tedu.springmvc;
  
  import cn.tedu.springmvc.config.SpringConfig;
  import cn.tedu.springmvc.config.SpringMvcConfig;
  import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
  
  /**
   * Spring MVC项目的初始化类
   */
  public class SpringMvcInitializer extends
          AbstractAnnotationConfigDispatcherServletInitializer {
  
      @Override
      protected Class<?>[] getRootConfigClasses() {
          // 返回自行配置的Spring相关内容的类
          return new Class[] { SpringConfig.class };
      }
  
      @Override
      protected Class<?>[] getServletConfigClasses() {
          // 返回自行配置的Spring MVC相关内容的类
          return new Class[] { SpringMvcConfig.class };
      }
  
      @Override
      protected String[] getServletMappings() {
          // 返回哪些路径是由Spring MVC框架处理的
          return new String[] { "*.do" };
      }
  
  }
  ```

- 最后，创建控制器类，用于接收客户端的某个请求，并简单的响应结果：

  **cn.tedu.springmvc.controller.UserController**

  ```java
  package cn.tedu.springmvc.controller;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.ResponseBody;
  
  @Controller // 必须是@Controller，不可以是其它组件注解
  public class UserController {
  
      public UserController() {
          System.out.println("UserController.UserController()");
      }
  
      // http://localhost:8080/springmvc01_war_exploded/login.do
      @RequestMapping("/login.do")
      @ResponseBody
      public String login() {
          return "UserController.login()";
      }
  
  }
  ```

- 全部完成后，启动项目，会自动打开浏览器并显示主页，在主页的地址栏URL上补充`/login.do`即可实现访问，并看到结果。











