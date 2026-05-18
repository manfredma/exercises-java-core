    # exercises-java-core

Java 8 核心知识练习项目，Maven 多模块结构。每个模块聚焦一个知识域，包内按职责分层（Demo 类在根包，模型类在 `.model`，辅助类在 `.util`/`.impl`/`.handler` 等子包）。

[English](README_EN.md)

## 模块总览

### java-basics — Java 语言基础语法

| 包                    | 内容                                            |
|----------------------|-----------------------------------------------|
| `lang.basic`         | 基础语法、运算符、枚举、字符串处理、类初始化顺序                      |
| `lang.string`        | 字符串 API、intern 机制、编码                          |
| `lang.collection`    | 集合框架（List/Map/Set）使用与原理                       |
| `lang.generic`       | 泛型机制、类型擦除、边界约束、通配符                            |
| `lang.datetime`      | 日期时间 API（LocalDate/DateTimeFormatter/Instant） |
| `lang.defaultmethod` | 接口默认方法、多继承冲突解析                                |

### java-lambda — Lambda 与函数式编程

| 包                  | 内容                                |
|--------------------|-----------------------------------|
| `lang.lambda`      | Lambda 表达式、函数式接口、ExecuteAround 模式 |
| `lang.functional`  | 函数式编程：柯里化、惰性求值、递归、持久化数据结构         |
| `lang.stream`      | Stream API：过滤/映射/规约/收集/分组/分区      |
| `lang.optional`    | Optional 链式调用、flatMap 组合          |
| `lang.refactoring` | 用 Lambda 重构设计模式：策略/观察者/工厂/模板方法    |
| `lang.dsl`         | DSL 构建风格：方法链/嵌套函数/Lambda/混合       |

### java-io — I/O 编程

| 包         | 内容                                          |
|-----------|---------------------------------------------|
| `io.bio`  | BIO 阻塞 IO（一对一、伪异步线程池模型）                     |
| `io.nio`  | NIO 非阻塞 IO（Channel/Buffer/Selector/Reactor） |
| `io.aio`  | AIO 异步 IO（CompletionHandler 回调链）            |
| `io.file` | 文件操作（Path/Files/属性）                         |

### java-serialization — 序列化

| 包                           | 内容                                     |
|-----------------------------|----------------------------------------|
| `serialization.jdk`         | JDK 原生序列化（Serializable/ObjectStream）   |
| `serialization.json`        | JSON：Jackson/Gson/Fastjson/JSON Schema |
| `serialization.messagepack` | MessagePack 二进制序列化                     |
| `serialization.protobuf`    | Protocol Buffers（.proto → 生成代码）        |
| `serialization.html`        | HTML 解析（Jsoup）                         |

### java-concurrency — 并发编程

| 包                               | 内容                                 |
|---------------------------------|------------------------------------|
| `concurrency.thread`            | Thread 基础、ThreadLocal、线程生命周期       |
| `concurrency.lock`              | ReentrantLock、Condition、AQS 自定义锁   |
| `concurrency.atomic`            | 原子类、CAS、线程安全边界                     |
| `concurrency.forkjoin`          | Fork/Join 框架、RecursiveTask/Action  |
| `concurrency.parallel`          | 并行流性能对比（JMH 基准测试）                  |
| `concurrency.completablefuture` | CompletableFuture 异步编排、多 Shop 价格聚合 |
| `concurrency.future`            | Future 基础用法                        |
| `concurrency.publish`           | 不安全发布、构造函数逸出                       |
| `concurrency.collection`        | 并发集合（ConcurrentHashMap 等）          |
| `concurrency.coroutine`         | 协程（Quasar Fiber、EA Async）          |

### jvm-all — JVM 内部原理

| 包                 | 内容                                           |
|-------------------|----------------------------------------------|
| `jvm.memory`      | 堆/栈/方法区/直接内存 OOM、对象布局（JOL）、Unsafe            |
| `jvm.gc`          | GC 触发条件、分配失败、System.gc 行为                    |
| `jvm.classloader` | 类加载机制、双亲委派、自定义 ClassLoader、热重载               |
| `jvm.proxy`       | JDK 动态代理                                     |
| `jvm.agent`       | Java Agent（Premain/字节码转换）                    |
| `jvm.invoke`      | 方法调用：反射/MethodHandle/invokedynamic/静态分派/动态分派 |
| `jvm.bytecode`    | 字节码操作：ASM/Byte Buddy/Javassist               |
| `jvm.optimize`    | JIT 优化：逃逸分析/伪共享/缓存行/指令重排/内联                  |
| `jvm.jni`         | JNI 本地方法调用                                   |

### low-level — 底层原理

| 包                   | 内容                          |
|---------------------|-----------------------------|
| `lowlevel.compiler` | 手写简易编译器（词法分析→语法分析→AST→解释执行） |
| `lowlevel.computer` | 计算机组成原理                     |

### java-net — 网络编程

| 包     | 内容                      |
|-------|-------------------------|
| `net` | Java 网络编程基础（Socket/URL） |

---

## 包内分层规范

每个知识域包内按职责分三层：

```
concurrency.completablefuture/        ← Demo 类（有 main 方法的演示入口）
concurrency.completablefuture.model/  ← 模型/数据类（POJO，演示用的数据载体）
concurrency.completablefuture.service/← 服务/实现类（无 main，被 Demo 调用）
concurrency.completablefuture.util/   ← 工具类（工具方法，如 Util.delay()）
```

---

## 快速开始

**环境要求：** JDK 8+、Maven 3.6+

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
```

## 添加新模块

```
/init-java-exercises <module-name> [描述]
```

详见 [CLAUDE.md](CLAUDE.md)。
