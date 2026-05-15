<!-- Generated: 2026-05-15 -->

# exercises-java-core

## Purpose

Java 8 核心知识练习项目，通过多模块结构组织不同主题的代码练习（集合、并发、IO、设计模式等）。
每个子模块独立聚焦一个主题，可单独编译和测试。

## Key Files

| File | Description |
|------|-------------|
| `pom.xml` | Maven 父 POM，统一管理依赖版本和插件配置 |
| `CLAUDE.md` | AI Coding 规范：构建命令、模块约定、代码风格 |
| `LICENSE` | MIT 开源协议 |

## Subdirectories

子模块按需添加，每个子模块有独立的 `AGENTS.md`。

| Directory | Purpose |
|-----------|---------|
| `<module>/` | 各练习子模块（见对应 `AGENTS.md`） |

## For AI Agents

### Working In This Directory

- 修改根 `pom.xml` 时，只操作 `<dependencyManagement>`、`<pluginManagement>` 和 `<modules>` 节
- 新增子模块：创建目录 → 写子模块 `pom.xml` → 在根 `pom.xml` 的 `<modules>` 中注册
- 所有 `mvn` 命令必须附加 `-Dsort.skip=true`

### Testing Requirements

```bash
mvn clean test -Dsort.skip=true
```

### Common Patterns

- 父 POM 只做依赖版本管理（`<dependencyManagement>`），不声明实际依赖
- 子模块按需引用父 POM 中管理的依赖，无需写版本号

## Dependencies

### External

- `junit:junit:4.13.2` — 单元测试框架
- `org.mockito:mockito-core:4.11.0` — Mock 框架
- `org.assertj:assertj-core:3.24.2` — 流式断言库

<!-- MANUAL: -->
