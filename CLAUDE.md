# exercises-java-core

Java 8 核心知识练习项目，Maven 8模块结构。

## 项目结构

```
exercises-java-core/
├── pom.xml               ← 父 POM，统一依赖版本管理
├── java-basics/          ← Java 语言基础语法
├── java-lambda/          ← Lambda 与函数式编程
├── java-io/              ← I/O 编程
├── java-serialization/   ← 序列化
├── java-concurrency/     ← 并发编程
├── jvm-all/              ← JVM 内部原理
├── low-level/            ← 底层原理（编译器/汇编/计算机组成）
└── java-net/             ← 网络编程
```

- `groupId`: `manfred.end`，`artifactId`: `exercises`，`version`: `1.0-SNAPSHOT`
- Java 版本: 8

## 包内分层规范

每个知识域包内按职责分三层：

```
xxx/              ← Demo 类（有 main 方法，演示入口）
xxx.model/        ← 模型/数据类（POJO，演示用数据载体，无业务逻辑）
xxx.util/         ← 工具类（无 main，被其他类调用）
xxx.impl/         ← 接口实现类
xxx.handler/      ← 事件/消息处理器（如 AIO/BIO/NIO 的 Handler）
xxx.service/      ← 服务类（完整功能实现，无 main，被 Demo 调用）
xxx.builder/      ← 构建器类（DSL 构建器）
xxx.template/     ← 模板类（抽象模板方法基类）
```

## 代码规范

- 包名格式：`manfred.exercises.<domain>.<topic>`
- 演示类命名：`XxxDemo`（有 main 方法）
- 不在 `src/main/java` 中使用 `@Test` 注解（测试机制只用于 `src/test/java`）
- 所有 public 类必须有中文类级 Javadoc
- 无 `@author`/`@date` 等元数据注释
- 子模块 pom 不写 `<version>`，版本统一在根 pom 管理

## 构建命令

```bash
# 编译所有模块
mvn clean compile

# 运行所有测试
mvn clean test

# 编译/测试单个模块
mvn clean compile -pl java-basics
mvn clean test -pl java-basics

# 运行指定测试类
mvn clean test -pl java-basics -Dtest=XxxTest

# 打包（跳过测试）
mvn clean package -DskipTests
```

## 添加新子模块

使用内置命令：`/init-java-exercises <module-name> [描述]`

或手动：
1. 在根目录创建模块目录及 `src/main/java/`、`src/test/java/`
2. 创建 `pom.xml`，parent 指向根 POM，依赖不写版本号
3. 在根 `pom.xml` 的 `<modules>` 中注册

子模块 pom.xml 模板：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>manfred.end</groupId>
        <artifactId>exercises</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>my-module</artifactId>
    <dependencies>
        <!-- 从根 pom dependencyManagement 按需引用，不写版本号 -->
    </dependencies>
</project>
```
