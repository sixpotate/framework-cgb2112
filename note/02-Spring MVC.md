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

以上`jackson-databind`依赖项中也有一个转换器，当Spring MVC调用的处理请求的方法的返回值是Spring MVC没有匹配的默认转换器时，会自动使用`jackson-databind`的转换器，而`jackson-databind`转换器就会解析方法的返回值，并将其处理为JSON格式的字符串，在响应头中将`Content-Type`设置为`application/json`。

注意：在Spring MVC项目中，还需要在Spring MVC的配置类（`SpringMvcConfig`）上添加`@EnableWebMvc`注解，否则响应时将导致出现HTTP的406错误。

**【示例代码】**

**cn.tedu.springmvc.vo.UserVO**

```java
package cn.tedu.springmvc.vo;

public class UserVO {

    private String username;
    private String password;
    private String email;

    // 请自行补充以上3个属性的Setter & Getter
}
```

`UserController`的代码片段：

```java
// http://localhost:8080/springmvc01_war_exploded/user/info.do
@GetMapping("/info.do")
public UserVO info() {
    UserVO userVO = new UserVO();
    userVO.setUsername("chengheng");
    userVO.setPassword("1234567890");
    userVO.setEmail("chengheng@qq.com");
    return userVO;
}
```

**SpringMvcConfig**（补充`@EnableWebMvc`注解）

```java
@Configuration // 此注解不是必须的
@EnableWebMvc
@ComponentScan("cn.tedu.springmvc") // 必须配置在当前配置类，不可配置在Spring的配置类
public class SpringMvcConfig implements WebMvcConfigurer {
}
```

## 6. 接收请求参数

在Spring MVC中，当需要接收客户端的请求参数时，只需要将各参数直接声明为处理请求的方法的参数即可，例如：

```java
// http://localhost:8080/springmvc01_war_exploded/user/reg.do?username=root&password=123456&age=25
@RequestMapping("/reg.do")
public String reg(String username, String password, Integer age) {
    System.out.println("username = " + username
            + ", password = " + password
            + ", age = " + age);
    return "OK";
}
```

需要注意：

- 如果客户端提交的请求中根本没有匹配名称的参数，则以上获取到的值将是`null`
- 如果客户端仅提交了参数名称，却没有值，则以上获取到的值将是`""`（长度为0的字符串）
- 如果客户端提交了匹配名称的参数，并且值是有效的，则可以获取到值
- 以上名称应该是由服务器端决定的，客户端需要根据以上名称来提交请求参数
- 声明参数时，可以按需将参数声明成期望的类型，例如以上将`age`声明为`Integer`类型
  - 注意：声明成`String`以外的类型时，应该考虑是否可以成功转换类型

当有必要的情况下，可以在以上各参数的声明之前添加`@RequestParam`注解，其作用主要有：

- 配置`name`属性：客户端将按照此配置的值提交请求参数，而不再是根据方法的参数名称来提交请求参数
- 配置`required`属性：是否要求客户端必须提交此请求参数，默认为`true`，如果不提交，则出现400错误，当设置为`false`时，如果不提交，则服务器端将此参数值视为`null`
- 配置`defaultValue`属性：配置此请求参数的默认值，当客户端没有提交此请求参数时，视为此值

另外，如果需要客户端提交的请求参数较多，可以将这些参数封装为自定义的数据类型，并将自定义的数据类型作为处理方法的参数即可，例如：

**cn.tedu.springmvc.dto.UserRegDTO**

```java
package cn.tedu.springmvc.dto;

public class UserRegDTO {

    private String username;
    private String password;
    private Integer age;

    // 生成Setters & Getters
    // 生成toString()
    
}
```

**UserController**（代码片段）

```java
// http://localhost:8080/springmvc01_war_exploded/user/reg.do?username=root&password=123456&age=25
@RequestMapping("/reg.do")
public String reg(UserRegDTO userRegDTO) {
    System.out.println(userRegDTO);
    return "OK";
}
```

需要注意，不要将`@RequestParam`添加在封装的类型之前。

另外，你也可以将多个请求参数区分开来，一部分直接声明为处理请求的方法的参数，另一部分封装起来。

## 7. 关于RESTful

百科资料：RESTFUL是一种网络应用程序的设计风格和开发方式，基于HTTP，可以使用XML格式定义或JSON格式定义。RESTFUL适用于移动互联网厂商作为业务接口的场景，实现第三方OTT调用移动网络资源的功能，动作类型为新增、变更、删除所调用资源。

RESTful的设计风格的**典型表现**就是：将某些唯一的请求参数的值放在URL中，使之成为URL的一部分，例如https://www.zhihu.com/question/28557115这个URL的最后一部分`28557115` 应该就是这篇贴子的id值，而不是使用例如`?id=28557115`这样的方式放在URL参数中。

注意：RESTful只是一种设计风格，并不是一种规定，也没有明确的或统一的执行方式！

如果没有明确的要求，以处理用户数据为例，可以将URL设计为：

- `/users`：查看用户列表
- `/users/9527`：查询id=9527的用户的数据
- `/users/9527/delete`：删除id=9527的用户的数据

在RESTful风格的URL中，大多是包含了某些请求参数的值，在使用Spring MVC框架时，当需要设计这类URL时，可以使用`{名称}`进行占位，并在处理请求的方法的参数列表中，使用`@PathVariable`注解请求参数，即可将占位符的实际值注入到请求参数中！

例如：

```java
// http://localhost:8080/springmvc01_war_exploded/user/3/info.do
@GetMapping("/{id}/info.do")
public UserVO info(@PathVariable Long id) {
    System.out.println("即将查询 id = " + id + " 的用户的信息……");
    UserVO userVO = new UserVO();
    userVO.setUsername("chengheng");
    userVO.setPassword("1234567890");
    userVO.setEmail("chengheng@qq.com");
    return userVO;
}
```

提示：在以上代码中，URL中使用的占位符是`{id}`，则方法的参数名称也应该是`id`，就可以直接匹配上！如果无法保证这2处的名称一致，则需要在`@PathVariable`注解中配置占位符中的名称，例如：

```java
@GetMapping("/{userId}/info.do")
public UserVO info(@PathVariable("userId") Long id) {
    // ...
}
```

在使用`{}`格式的占位符时，还可以结合正则表达式进行匹配，其基本语法是：

```java
{占位符名称:正则表达式}
```

例如：

```java
@GetMapping("/{id:[0-9]+}/info.do")
```

当设计成以上URL时，仅当占位符位置的是纯数字的URL才会被匹配上，如果不是纯数字的刚出现404错误页面。

并且，以上模式的多种不冲突的正则表达式是可以同时存在的，例如：

```java
@GetMapping("/{id:[0-9]+}/info.do")
public UserVO info(@PathVariable Long id) {
    System.out.println("即将查询 id = " + id + " 的用户的信息……");
    // ...
}

@GetMapping("/{username:[a-zA-Z]+}/info.do")
public UserVO info(@PathVariable String username) {
    System.out.println("即将查询 用户名 = " + username + " 的用户的信息……");
    // ...
}
```

甚至，还可以存在不使用正则表达式，但是URL格式几乎一样的配置：

```java
@GetMapping("/{id:[0-9]+}/info.do")
public UserVO info(@PathVariable Long id) {
    System.out.println("即将查询 id = " + id + " 的用户的信息……");
    // ...
}

@GetMapping("/{username:[a-zA-Z]+}/info.do")
public UserVO info(@PathVariable String username) {
    System.out.println("即将查询 用户名 = " + username + " 的用户的信息……");
    // ...
}

// 【以下是新增的】
// http://localhost:8080/springmvc01_war_exploded/user/list/info.do
@GetMapping("/list/info.do")
public UserVO list() {
    System.out.println("即将查询 用户的列表 的信息……");
    // ...
}
```

最终执行时，如果使用`/user/list/info.do`，则会匹配到以上代码中的最后一个方法，并不会因为这个URL还能匹配第2个方法配置的`{username:[a-zA-Z]+}`而产生冲突。所以，使用了占位符的做法并不影响精准匹配的路径。





















## 附：关于POJO

所有用于封装属性的类型都可以统称为POJO。

常见的POJO后缀有：BO、DO、VO、DTO等，不同的后缀表示不同的意义，例如：VO = Value Object / View Object，DTO = Data Transfer Object ……

在一个项目中，哪些情景下使用哪种后缀并没有统一的规定，通常是各项目内部决定。

注意：在为封装属性的类进行命名时，以上BO、DO、VO、DTO等这些后缀的每一个字母都应该是大写的！







