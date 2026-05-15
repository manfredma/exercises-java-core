# exercises-java-core

Java 8 核心知识练习项目，采用 Maven 扁平多模块结构组织。每个模块聚焦一个独立知识点，可单独编译和测试。

[English](README_EN.md)

## 模块总览

### Java 语言基础

| 模块 | 内容 |
|------|------|
| `java-basic` | 基础语法、数据类型、字符串、异常、反射等 |
| `java-collection` | 集合框架（List/Map/Set）使用与原理 |
| `java-generic` | 泛型机制、类型擦除、通配符 |
| `java-lambda` | Lambda 表达式基础 |
| `java-thread` | Thread 基础、ThreadLocal、线程生命周期 |
| `java-gc` | GC 触发与回收行为观察 |
| `java8` | Java 8 新特性：Stream、Optional、函数式接口等（含《Java 8 in Action》练习） |

### Java I/O

| 模块 | 内容 |
|------|------|
| `java-io-bio` | 传统阻塞 IO（BIO） |
| `java-io-nio` | 非阻塞 IO、Channel、Buffer、Selector |
| `java-io-aio` | 异步 IO（AIO） |
| `java-io-file` | 文件操作、Path、Files |

### 序列化

| 模块 | 内容 |
|------|------|
| `jdk-serialization` | JDK 原生序列化 |
| `java-serialization-json` | JSON 序列化：Jackson、Gson、Fastjson、JSON Schema |
| `java-serialization-msgpack` | MessagePack 二进制序列化 |
| `java-serialization-protobuf` | Protocol Buffers |
| `java-serialization-html` | HTML 解析（Jsoup） |

### 并发

| 模块 | 内容 |
|------|------|
| `jdk-concurrent` | JUC 核心：Lock、AQS、原子类、线程池、CompletableFuture |
| `java-concurrency-lock` | 锁机制深入：ReentrantLock、Condition |
| `multi-task` | 多任务并行编排框架（TTL、Spring） |
| `parseq` | LinkedIn ParSeq 异步任务编排 |
| `java-reactive-reactor` | Project Reactor 响应式编程 |
| `java-reactive-rsocket` | RSocket 响应式通信协议 |
| `java-reactive-rxjava1` | RxJava 1.x |
| `java-reactive-rxjava2` | RxJava 2.x |
| `quasar` | Quasar 协程（Java 协程库） |
| `ea-async` | EA Async 异步转同步语法糖 |

### JVM

| 模块 | 内容 |
|------|------|
| `jvm-memory` | 内存结构：堆、栈、方法区、直接内存、JOL 对象布局 |
| `jvm-gc` | GC 算法与调优 |
| `jvm-classloader` | 类加载机制、双亲委派、自定义 ClassLoader |
| `jvm-proxy` | JDK 动态代理 |
| `jvm-agent` | Java Agent（Premain/Agentmain） |
| `jvm-invoke` | 方法调用：反射、MethodHandle、invokedynamic |
| `jvm-method-invoke` | 方法调用字节码指令深入 |
| `jvm-native` | JNI 本地方法调用 |
| `jvm-off-heap` | 堆外内存：DirectByteBuffer、Unsafe |
| `jvm-optimize` | JVM 优化：逃逸分析、伪共享、缓存行、内联 |
| `jvm-metaspace` | Metaspace 原理与观察 |

### 字节码操作

| 模块 | 内容 |
|------|------|
| `jvm-byte-basic` | 字节码基础、class 文件结构 |
| `javassist` | Javassist 字节码操作库 |
| `byte-buddy` | Byte Buddy 字节码生成与增强 |

### 底层原理

| 模块 | 内容 |
|------|------|
| `asm-compiler` | 手写简易编译器（词法分析、语法分析、AST） |
| `asm-nasm` | NASM 汇编语言练习 |
| `computer-organization-architecture` | 计算机组成原理 |

## 快速开始

### 环境要求

- JDK 8+
- Maven 3.6+

### 编译

```bash
# 编译所有模块
mvn clean compile -Dsort.skip=true

# 编译指定模块
mvn clean compile -pl java-basic -Dsort.skip=true
```

### 测试

```bash
# 运行所有测试
mvn clean test -Dsort.skip=true

# 运行指定模块测试
mvn clean test -pl java-basic -Dsort.skip=true

# 运行指定测试类
mvn clean test -pl java-basic -Dtest=XxxTest -Dsort.skip=true
```

## 添加新模块

使用内置命令快速初始化：

```
/init-java-exercises <module-name> [描述]
```

或手动创建：

1. 在根目录创建模块目录及标准 Maven 目录结构
2. 创建 `pom.xml`，parent 指向根 POM
3. 在根 `pom.xml` 的 `<modules>` 中注册

详见 [CLAUDE.md](CLAUDE.md)。

## 项目结构

```
exercises-java-core/
├── pom.xml                    # 父 POM（依赖统一管理）
├── CLAUDE.md                  # AI Coding 规范
├── AGENTS.md                  # AI 导航文档
├── java-basic/                # 各练习模块（扁平结构）
├── java8/
├── jvm-memory/
└── ...
```

## 技术栈

- Java 8
- Maven 3.x（多模块）
- JUnit 4 + Mockito + AssertJ
