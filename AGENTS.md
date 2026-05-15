<!-- Generated: 2026-05-15 -->

# exercises-java-core

## Purpose

Java 8 核心知识练习项目。扁平 Maven 多模块结构：所有练习模块直接挂根 POM，无嵌套聚合层，每模块聚焦单一主题，独立编译测试。

## Key Files

| File | Description |
|------|-------------|
| `pom.xml` | 父 POM，`<modules>` 按分组注释列出所有叶子模块，统一依赖版本 |
| `CLAUDE.md` | AI Coding 规范：构建命令、模块约定、代码风格 |
| `README.md` | 中文项目说明 |
| `README_EN.md` | 英文项目说明 |

## Module Map

### Java 基础
`java-basic` · `java-collection` · `java-generic` · `java-lambda` · `java-thread` · `java-gc` · `java8`

### I/O
`java-io-bio` · `java-io-nio` · `java-io-aio` · `java-io-file`

### 序列化
`jdk-serialization` · `java-serialization-json` · `java-serialization-msgpack` · `java-serialization-protobuf` · `java-serialization-html`

### 并发
`jdk-concurrent` · `java-concurrency-lock` · `multi-task` · `parseq` · `java-reactive-reactor` · `java-reactive-rsocket` · `java-reactive-rxjava1` · `java-reactive-rxjava2` · `quasar` · `ea-async`

### JVM
`jvm-memory` · `jvm-gc` · `jvm-classloader` · `jvm-proxy` · `jvm-agent` · `jvm-invoke` · `jvm-method-invoke` · `jvm-native` · `jvm-off-heap` · `jvm-optimize` · `jvm-metaspace`

### 字节码
`jvm-byte-basic` · `javassist` · `byte-buddy`

### 底层
`asm-compiler` · `asm-nasm` · `computer-organization-architecture`

## For AI Agents

### Working In This Directory

- 修改根 `pom.xml` 时，只操作 `<dependencyManagement>` 和 `<modules>` 节
- 新增模块：创建目录 → 写 `pom.xml`（parent 指向根）→ 在根 `<modules>` 对应分组注释下注册 → 创建 `AGENTS.md`
- 所有 `mvn` 命令必须附加 `-Dsort.skip=true`
- 每个模块目录下有独立 `AGENTS.md`，描述该模块的包结构和学习重点

### Testing Requirements

```bash
mvn clean test -Dsort.skip=true
```

### Common Patterns

- 父 POM 集中管理依赖版本，子模块按需引用无需写版本号
- 测试框架：JUnit 4 + Mockito + AssertJ
- 包名：`manfred.end.<topic>` 或 `manfred.<topic>`

## Dependencies

### External（父 POM 管理的常用依赖）

- `junit:junit:4.13.1` — 测试框架
- `org.mockito:mockito-core:2.23.0` — Mock
- `org.openjdk.jmh:jmh-core:1.23` — 微基准测试
- `com.google.guava:guava:30.1-jre` — Guava 工具库
- `org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.20` — Kotlin 支持

<!-- MANUAL: -->
