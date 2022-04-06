## 1. 关于创建数据库

创建数据库的语法是：

```mysql
CREATE DATABASE 数据库名称;
```

当某个项目规模特别大时，应该根据数据之间的关系，尽可能的拆为多个数据库。

## 2. 关于使用数据库

使用数据库的语法是：

```mysql
USE 数据库名称;
```

提示：以上语法中的分号是可选的。

## 3. 创建数据表

创建数据表的基本语法是：

```mysql
CREATE TABLE 数据表名称 (字段设计列表) CHARSET 字符编码 COMMENT 注释;
```

注意：主流的设计中，数据表的编码强烈建议配置为`utf8mb4`（过低版本的MySQL不支持）。

在设计字段时，基本语法是：

```java
字段名 字段类型 字段约束 comment 注释
```

简单示例：

```mysql
create table ams_admin (
    id bigint unsigned auto_increment,
    username varchar(50) default null unique comment '用户名',
    password char(64) default null comment '密码（密文）',
    nickname varchar(50) default null comment '昵称',
    avatar varchar(255) default null comment '头像URL',
    phone varchar(50) default null unique comment '手机号码',
    email varchar(50) default null unique comment '电子邮箱',
    description varchar(255) default null comment '描述',
    is_enable tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    last_login_ip varchar(50) default null comment '最后登录IP地址（冗余）',
    login_count int unsigned default 0 comment '累计登录次数（冗余）',
    gmt_last_login datetime default null comment '最后登录时间（冗余）',
    gmt_create datetime default null comment '数据创建时间',
    gmt_modified datetime default null comment '数据最后修改时间',
    primary key (id)
) comment '管理员表' charset utf8mb4;
```









