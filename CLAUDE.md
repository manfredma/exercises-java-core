# exercises-java-core

Java 8 核心知识练习项目，Maven 7模块结构。

## 项目结构

```
exercises-java-core/
├── pom.xml               ← 父 POM，统一依赖版本管理
├── java-basics/          ← Java 语言基础
├── java-io/              ← I/O 操作
├── java-serialization/   ← 序列化
├── java-concurrency/     ← 并发
├── jvm-all/              ← JVM 内部
├── low-level/            ← 底层/系统
└── java-net/             ← 网络编程
```

- `groupId`: `manfred.end`，`artifactId`: `exercises`，`version`: `1.0-SNAPSHOT`
- Java 版本: 8
- 包名规范: `manfred.exercises.<domain>.<topic>`

## 构建命令

```bash
# 编译所有模块
mvn clean compile

# 运行所有测试
mvn clean test

# 编译/测试单个模块
mvn clean compile -pl java-basics
mvn clean test -pl java-basics

# 打包（跳过测试）
mvn clean package -DskipTests
```

## 添加新子模块

使用内置命令：`/init-java-exercises <module-name> [描述]`

或手动：
1. 在根目录创建模块目录及 `src/main/java/`、`src/test/java/`
2. 创建 `pom.xml`，parent 指向根 POM，依赖版本从根 POM 继承（不写 `<version>`）
3. 在根 `pom.xml` 的 `<modules>` 中注册

子模块 pom.xml 最简模板：
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

## 代码规范

- 包名格式：`manfred.exercises.<domain>.<topic>`
- 演示类命名：`XxxDemo` 或 `XxxExample`
- 测试类命名：`XxxTest.java`（仅限 `src/test/java`，有 `@Test` 注解）
- `src/main/java` 下的类用 `main` 方法启动，不使用 `@Test`
- 使用 AssertJ 的 `assertThat()` 断言（测试代码）
