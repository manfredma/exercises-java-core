# exercises-java-core

Java 8 核心知识练习项目，Maven 多模块管理。

## 项目结构

```
exercises-java-core/        ← 父 POM，统一依赖管理
└── <module>/               ← 各练习子模块（按需添加）
    ├── src/main/java/
    └── src/test/java/
```

- `groupId`: `manfred.exercises`
- Java 版本: 8
- 测试框架: JUnit 4 + Mockito + AssertJ

## 构建命令

```bash
# 编译
mvn clean compile -Dsort.skip=true

# 运行所有测试
mvn clean test -Dsort.skip=true

# 运行单个模块测试
mvn clean test -pl <module-name> -Dsort.skip=true

# 运行指定测试类
mvn clean test -pl <module-name> -Dtest=XxxTest -Dsort.skip=true

# 打包（跳过测试）
mvn clean package -DskipTests -Dsort.skip=true
```

> **重要**：所有 `mvn` 命令必须附加 `-Dsort.skip=true`，避免 sortpom 插件重排 pom.xml。
> 构建相关命令（compile/test/package/install）必须加 `clean`。

## 添加新子模块

1. 在根目录创建子模块目录
2. 在子模块目录创建 `pom.xml`，parent 指向根 POM
3. 在根 `pom.xml` 的 `<modules>` 中注册新模块
4. 按标准 Maven 目录布局创建 `src/main/java/` 和 `src/test/java/`

子模块 pom.xml 最简模板：
```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>manfred.exercises</groupId>
    <artifactId>exercises-java-core</artifactId>
    <version>1.0.0-SNAPSHOT</version>
  </parent>
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

- 包名格式：`manfred.exercises.<module>.<topic>`
- 每个练习对应一个独立的类和测试类
- 测试类命名：`XxxTest.java`（JUnit 4 风格）
- 方法命名：`test_<场景>_<期望结果>` 或直接描述行为的英文名

## 测试规范

- 每个实现类必须有对应测试类
- 使用 AssertJ 的 `assertThat()` 风格断言（比 JUnit 原生断言更易读）
- Mock 用 Mockito，避免在单元测试中依赖外部资源
