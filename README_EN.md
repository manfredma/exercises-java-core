# exercises-java-core

Java 8 core knowledge exercises organized as a flat Maven multi-module project. Each module focuses on a single topic and can be compiled and tested independently.

[中文](README.md)

## Module Overview

### Java Language Basics

| Module | Content |
|--------|---------|
| `java-basic` | Syntax, data types, strings, exceptions, reflection |
| `java-collection` | Collection framework (List/Map/Set) usage and internals |
| `java-generic` | Generics, type erasure, wildcards |
| `java-lambda` | Lambda expression fundamentals |
| `java-thread` | Thread basics, ThreadLocal, thread lifecycle |
| `java-gc` | GC triggering and reclamation behavior |
| `java8` | Java 8 features: Stream, Optional, functional interfaces (includes *Java 8 in Action* exercises) |

### Java I/O

| Module | Content |
|--------|---------|
| `java-io-bio` | Traditional blocking I/O (BIO) |
| `java-io-nio` | Non-blocking I/O, Channel, Buffer, Selector |
| `java-io-aio` | Asynchronous I/O (AIO) |
| `java-io-file` | File operations, Path, Files API |

### Serialization

| Module | Content |
|--------|---------|
| `jdk-serialization` | JDK native serialization |
| `java-serialization-json` | JSON: Jackson, Gson, Fastjson, JSON Schema |
| `java-serialization-msgpack` | MessagePack binary serialization |
| `java-serialization-protobuf` | Protocol Buffers |
| `java-serialization-html` | HTML parsing with Jsoup |

### Concurrency

| Module | Content |
|--------|---------|
| `jdk-concurrent` | JUC core: Lock, AQS, atomics, thread pools, CompletableFuture |
| `java-concurrency-lock` | Deep dive into ReentrantLock, Condition |
| `multi-task` | Parallel task orchestration (TTL, Spring) |
| `parseq` | LinkedIn ParSeq async task composition |
| `java-reactive-reactor` | Project Reactor reactive programming |
| `java-reactive-rsocket` | RSocket reactive communication protocol |
| `java-reactive-rxjava1` | RxJava 1.x |
| `java-reactive-rxjava2` | RxJava 2.x |
| `quasar` | Quasar coroutines for Java |
| `ea-async` | EA Async — async/await syntax sugar for Java |

### JVM

| Module | Content |
|--------|---------|
| `jvm-memory` | Memory layout: heap, stack, metaspace, off-heap, JOL object layout |
| `jvm-gc` | GC algorithms and tuning |
| `jvm-classloader` | Class loading, parent delegation, custom ClassLoader |
| `jvm-proxy` | JDK dynamic proxy |
| `jvm-agent` | Java Agent (Premain / Agentmain) |
| `jvm-invoke` | Method invocation: reflection, MethodHandle, invokedynamic |
| `jvm-method-invoke` | Bytecode-level method invocation deep dive |
| `jvm-native` | JNI native method calls |
| `jvm-off-heap` | Off-heap memory: DirectByteBuffer, Unsafe |
| `jvm-optimize` | JVM optimizations: escape analysis, false sharing, cache lines, inlining |
| `jvm-metaspace` | Metaspace internals |

### Bytecode Manipulation

| Module | Content |
|--------|---------|
| `jvm-byte-basic` | Bytecode basics, .class file structure |
| `javassist` | Bytecode manipulation with Javassist |
| `byte-buddy` | Bytecode generation and enhancement with Byte Buddy |

### Low Level

| Module | Content |
|--------|---------|
| `asm-compiler` | Hand-written mini compiler (lexer, parser, AST) |
| `asm-nasm` | NASM assembly language exercises |
| `computer-organization-architecture` | Computer organization and architecture |

## Getting Started

### Requirements

- JDK 8+
- Maven 3.6+

### Build

```bash
# Build all modules
mvn clean compile -Dsort.skip=true

# Build a specific module
mvn clean compile -pl java-basic -Dsort.skip=true
```

### Test

```bash
# Run all tests
mvn clean test -Dsort.skip=true

# Run tests for a specific module
mvn clean test -pl java-basic -Dsort.skip=true

# Run a specific test class
mvn clean test -pl java-basic -Dtest=XxxTest -Dsort.skip=true
```

## Adding a New Module

Use the built-in command:

```
/init-java-exercises <module-name> [description]
```

Or manually:

1. Create the module directory with standard Maven layout
2. Create `pom.xml` with parent pointing to the root POM
3. Register the module in the root `pom.xml` `<modules>` section

See [CLAUDE.md](CLAUDE.md) for details.

## Project Layout

```
exercises-java-core/
├── pom.xml                    # Parent POM (centralized dependency management)
├── CLAUDE.md                  # AI coding conventions
├── AGENTS.md                  # AI navigation docs
├── java-basic/                # Exercise modules (flat structure)
├── java8/
├── jvm-memory/
└── ...
```

## Tech Stack

- Java 8
- Maven 3.x (multi-module)
- JUnit 4 + Mockito + AssertJ
