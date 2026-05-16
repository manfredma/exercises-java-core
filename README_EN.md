# exercises-java-core

Java 8 core knowledge exercises organized as a Maven multi-module project. Each module covers one knowledge domain, with packages layered by responsibility (Demo classes at root, model classes in `.model`, helpers in `.util`/`.impl`/`.handler` subpackages).

[中文](README.md)

## Module Overview

### java-basics — Java Language Syntax

| Package | Content |
|---|---|
| `lang.basic` | Syntax, operators, enums, string traps, class init order |
| `lang.string` | String API, intern mechanism, encoding |
| `lang.collection` | Collection framework (List/Map/Set) |
| `lang.generic` | Generics, type erasure, bounds, wildcards |
| `lang.datetime` | Date/time API (LocalDate/DateTimeFormatter/Instant) |
| `lang.defaultmethod` | Interface default methods, multi-inheritance conflict resolution |

### java-lambda — Lambda & Functional Programming

| Package | Content |
|---|---|
| `lang.lambda` | Lambda expressions, functional interfaces, ExecuteAround pattern |
| `lang.functional` | Functional programming: currying, lazy evaluation, recursion, persistent structures |
| `lang.stream` | Stream API: filter/map/reduce/collect/group/partition |
| `lang.optional` | Optional chaining, flatMap composition |
| `lang.refactoring` | Refactoring design patterns with Lambda: strategy/observer/factory/template method |
| `lang.dsl` | DSL building styles: method chaining/nested functions/Lambda/mixed |

### java-io — I/O Programming

| Package | Content |
|---|---|
| `io.bio` | BIO blocking IO (one-to-one, pseudo-async thread pool) |
| `io.nio` | NIO non-blocking IO (Channel/Buffer/Selector/Reactor) |
| `io.aio` | AIO async IO (CompletionHandler callback chain) |
| `io.file` | File operations (Path/Files/attributes) |

### java-serialization — Serialization

| Package | Content |
|---|---|
| `serialization.jdk` | JDK native serialization (Serializable/ObjectStream) |
| `serialization.json` | JSON: Jackson/Gson/Fastjson/JSON Schema |
| `serialization.messagepack` | MessagePack binary serialization |
| `serialization.protobuf` | Protocol Buffers (.proto → generated code) |
| `serialization.html` | HTML parsing (Jsoup) |

### java-concurrency — Concurrent Programming

| Package | Content |
|---|---|
| `concurrency.thread` | Thread basics, ThreadLocal, thread lifecycle |
| `concurrency.lock` | ReentrantLock, Condition, AQS custom lock |
| `concurrency.atomic` | Atomic classes, CAS, thread-safe bounds |
| `concurrency.forkjoin` | Fork/Join framework, RecursiveTask/Action |
| `concurrency.parallel` | Parallel stream performance benchmarks (JMH) |
| `concurrency.completablefuture` | CompletableFuture async orchestration, multi-shop price aggregation |
| `concurrency.future` | Future basics |
| `concurrency.publish` | Unsafe publication, constructor escape |
| `concurrency.collection` | Concurrent collections |
| `concurrency.coroutine` | Coroutines (Quasar Fiber, EA Async) |

### jvm-all — JVM Internals

| Package | Content |
|---|---|
| `jvm.memory` | Heap/stack/metaspace/off-heap OOM, object layout (JOL), Unsafe |
| `jvm.gc` | GC triggers, allocation failure, System.gc behavior |
| `jvm.classloader` | Class loading, parent delegation, custom ClassLoader, hot reload |
| `jvm.proxy` | JDK dynamic proxy |
| `jvm.agent` | Java Agent (Premain / bytecode transformation) |
| `jvm.invoke` | Method invocation: reflection/MethodHandle/invokedynamic/static & dynamic dispatch |
| `jvm.bytecode` | Bytecode manipulation: ASM / Byte Buddy / Javassist |
| `jvm.optimize` | JIT optimizations: escape analysis/false sharing/cache lines/instruction reordering/inlining |
| `jvm.jni` | JNI native method calls |

### low-level — Low-level Internals

| Package | Content |
|---|---|
| `lowlevel.compiler` | Hand-written mini compiler (lexer → parser → AST → interpreter) |
| `lowlevel.computer` | Computer organization and architecture |

### java-net — Network Programming

| Package | Content |
|---|---|
| `net` | Java networking basics (Socket/URL) |

---

## Package Layering Convention

Each domain package is layered by responsibility:

```
concurrency.completablefuture/         ← Demo classes (with main() entry point)
concurrency.completablefuture.model/   ← Model/data classes (POJOs, demo data)
concurrency.completablefuture.service/ ← Service/impl classes (no main, called by Demo)
concurrency.completablefuture.util/    ← Utility classes (helper methods)
```

---

## Getting Started

**Requirements:** JDK 8+, Maven 3.6+

```bash
# Build all modules
mvn clean compile

# Run all tests
mvn clean test

# Build/test a specific module
mvn clean compile -pl java-basics
mvn clean test -pl java-basics

# Run a specific test class
mvn clean test -pl java-basics -Dtest=XxxTest
```

## Adding a New Module

```
/init-java-exercises <module-name> [description]
```

See [CLAUDE.md](CLAUDE.md) for details.
