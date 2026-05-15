# Test

运行测试（支持指定模块或测试类）。

```bash
mvn clean test -Dsort.skip=true $ARGUMENTS
```

用法示例：
- `/test` — 运行所有测试
- `/test -pl exercises-collections` — 运行指定模块的测试
- `/test -pl exercises-collections -Dtest=LinkedListTest` — 运行指定测试类
