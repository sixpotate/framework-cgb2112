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

关于以上案例：

- 当启动Tomcat时，会自动将项目打包并部署到Tomcat，通过自动打开的浏览器中的URL即可访问主页，在URL中有很长一段是例如 `springmvc01_war_explored` 这一段是不可以删除的，其它的各路径必须补充在其之后，例如 `/login.do` 就必须在此之后
- 当启动Tomcat时，项目一旦部署成功，就会自动创建并加载`AbstractAnnotationConfigDispatcherServletInitializer`的子类，即当前项目中自定义的`SpringMvcInitialier`，无论这个类放在哪个包中，都会自动创建并加载，由于会自动调用这个类中所有方法，所以会将Spring MVC框架处理的请求路径设置为 `*.do`，并执行对 `cn.tedu.springmvc` 的组件扫描，进而会创建 `UserController` 的对象，由于在 `UserController` 中配置的方法使用了 `@RequestMapping("/login.do")`，则此时还将此方法与`/login.do`进行了绑定，以至于后续随时访问`/login.do`时都会执行此方法
- 注意：组件扫描必须配置在Spring MVC的配置类中
- 注意：控制器类上的注解必须是`@Controller`，不可以是`@Component`、`@Service`、`@Repository`

## 4. 关于`@RequestMapping`注解

`@RequestMapping`注解的**主要作用**是配置**请求路径**与**处理请求的方法**的映射关系，例如将此注解添加在控制器中某个方法之前：

```java
// http://localhost:8080/springmvc01_war_exploded/login.do
@RequestMapping("/login.do")
@ResponseBody
public String login() {
    return "UserController.login()";
}
```

就会将注解中配置的路径与注解所在的方法对应上！

除了方法之前，此注解还可以添加在控制器类之前，例如：

```java
@Controller
@RequestMapping("/user")
public class UserController {
}
```

一旦在类上添加了此注解并配置路径，则每个方法实际映射到的请求路径都是“类上的`@RequestMapping`配置的路径 + 方法上的`@RequestMapping`配置的路径”。

通常，在项目中，推荐为每一个控制器类都配置此注解，以指定某个URL前缀。

在使用`@RequestMapping`配置路径时，并不要求各路径使用 `/` 作为第1个字符！

另外，在`@RequestMapping`还可以配置：

- 请求方式
- 请求头
- 响应头
- 等等

所以，在`@RequestMapping`注解中，增加配置`method`属性，可以限制客户端的请求方式，例如可以配置为：

```java
@RequestMapping(value = "/login.do", method = RequestMethod.POST)
@ResponseBody
public String login() {
    return "UserController.login()";
}
```

如果按照以上代码，则`/login.do`路径只能通过`POST`方式发起请求才可以被正确的处理，如果使用其它请求方式（例如`GET`），则会导致HTTP的405错误。

如果没有配置`method`属性，则表示可以使用任何请求方式，包括：

```
GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
```

另外，Spring MVC框架还提供了`@RequestMapping`的相关注解，例如：

- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`
- 等等

这些注解就是已经限制了请求方式的注解！以`@GetMapping`为例，就限制了请求方式必须是`GET`，除此以外，使用方式与`@RequestMapping`完全相同！

所以，在实际应用中，在类的上方肯定使用`@RequestMapping`（其它的`@XxxMapping`不可以加在类上），方法上一般都使用`@GetMapping`、`@PostMapping`等注解，除非在极特殊的情况下，某些请求同时允许多种请求方式，才会在方法上使用`@RequestMapping`。

# 5. 关于`@ResponseBody`注解

`@ResponseBody`注解表示：响应正文。

一旦配置为“响应正文”，则处理请求的方法的返回值就会直接响应到客户端去！

如果没有配置为“响应正文”，则处理请求的方法的返回值表示“视图组件的名称”，当方法返回后，服务器端并不会直接响应，而是根据“视图组件的名称”在服务器端找到对应的视图组件，并处理，最后，将处理后的视图响应到客户端去，这不是**前后端分离**的做法！

可以在需要正文的方法上添加`@ResponseBody`注解，由于开发模式一般相对统一，所以，一般会将`@ResponseBody`添加在控制器类上，表示此控制器类中所有处理请求的方法都将响应正文！

在Spring MVC框架中，还提供了`@RestController`注解，它同时具有`@Controller`和`@ResponseBody`注解的效果，所以，在响应正文的控制器上，只需要使用`@RestController`即可，不必再添加`@Controller`和`@ResponseBody`注解。

关于响应正文，Spring MVC内置了一系列的转换器（Converter），用于将方法的返回值转换为响应到客户端的数据（并根据HTTP协议补充了必要的数据），并且，Spring MVC会根据方法的返回值不同，自动选取某个转换器，例如，当方法的返回值是`String`时，会自动使用`StringHttpMessageConverter`这个转换器，这个转换器的特点就是直接将方法返回的字符串作为响应的正文，并且，其默认的响应文档的字符集是ISO-8859-1，所以在默认情况并不支持非ASCII字符（例如中文）。

在实际应用中，不会使用`String`作为处理请求的方法的返回值类型，主要是因为普通的字符串不足以清楚的表现多项数据，如果自行组织成JSON或其它某种格式的字符串成本太高！

通常，建议向客户端响应JSON格式的字符串，应该在项目中添加`jackson-databind`的依赖项：

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.3</version>
</dependency>
```





```
{
	"username": "administrator",
	"password": "1234567890",
	"email": "admin@tedu.cn"
}
```









