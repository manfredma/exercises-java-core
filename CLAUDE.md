# exercises-java-core

Java 8 核心知识练习项目，Maven 扁平多模块结构（所有练习模块直接挂根 POM，无嵌套聚合层）。

## 项目结构

```
exercises-java-core/
├── pom.xml               ← 父 POM，统一依赖管理，<modules> 列出所有叶子模块
├── java-basic/           ← 各练习模块（扁平，直接含 src/）
├── java8/
├── jvm-memory/
└── ...
```

### 模块分组

| 分组 | 模块 |
|------|------|
| Java 基础 | `java-basic` `java-collection` `java-generic` `java-lambda` `java-thread` `java-gc` `java8` |
| I/O | `java-io-bio` `java-io-nio` `java-io-aio` `java-io-file` |
| 序列化 | `jdk-serialization` `java-serialization-json` `java-serialization-msgpack` `java-serialization-protobuf` `java-serialization-html` |
| 并发 | `jdk-concurrent` `java-concurrency-lock` `multi-task` `parseq` `java-reactive-reactor` `java-reactive-rsocket` `java-reactive-rxjava1` `java-reactive-rxjava2` `quasar` `ea-async` |
| JVM | `jvm-memory` `jvm-gc` `jvm-classloader` `jvm-proxy` `jvm-agent` `jvm-invoke` `jvm-method-invoke` `jvm-native` `jvm-off-heap` `jvm-optimize` `jvm-metaspace` |
| 字节码 | `jvm-byte-basic` `javassist` `byte-buddy` |
| 底层 | `asm-compiler` `asm-nasm` `computer-organization-architecture` |

- `groupId`: `manfred.end`，`artifactId`: `exercises`，`version`: `1.0-SNAPSHOT`
- Java 版本: 8

## 构建命令

```bash
# 编译所有模块
mvn clean compile -Dsort.skip=true

# 运行所有测试
mvn clean test -Dsort.skip=true

# 编译/测试单个模块
mvn clean compile -pl java-basic -Dsort.skip=true
mvn clean test -pl java-basic -Dsort.skip=true

# 运行指定测试类
mvn clean test -pl java-basic -Dtest=XxxTest -Dsort.skip=true

# 打包（跳过测试）
mvn clean package -DskipTests -Dsort.skip=true
```

> **重要**：所有 `mvn` 命令必须附加 `-Dsort.skip=true`，避免 sortpom 插件重排 pom.xml。
> 构建相关命令必须加 `clean`。

## 添加新子模块

使用内置命令：`/init-java-exercises <module-name> [描述]`

或手动：
1. 在根目录创建模块目录
2. 创建 `pom.xml`，parent 指向根 POM（groupId=`manfred.end`，artifactId=`exercises`）
3. 在根 `pom.xml` 的 `<modules>` 对应分组注释下注册
4. 创建 `src/main/java/` 和 `src/test/java/`
5. 创建模块级 `AGENTS.md`

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
    <artifactId>exercises-<name></artifactId>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>
</project>
```

## 代码规范

- 包名格式：`manfred.end.<topic>` 或 `manfred.<topic>`
- 每个练习对应一个独立的类和测试类
- 测试类命名：`XxxTest.java`（JUnit 4 风格）
- 使用 AssertJ 的 `assertThat()` 断言
- Mock 用 Mockito
