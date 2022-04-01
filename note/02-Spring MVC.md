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

  











