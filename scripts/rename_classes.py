#!/usr/bin/env python3
"""批量重命名 Java 类：文件重命名 + class 声明 + 所有内部引用"""
import os, re, shutil

ROOT = "/Users/maxingfang/IdeaProjects/github/exercises-java-core"

# (旧文件相对路径, 新类名)
RENAMES = [
    # Boot.java
    ("java-basics/src/main/java/manfred/exercises/concurrency/collection/Boot.java", "ConcurrentCollectionDemo"),
    ("java-basics/src/main/java/manfred/exercises/concurrency/publish/Boot.java", "UnsafePublishDemo"),
    ("java-basics/src/main/java/manfred/exercises/concurrency/thread/Boot.java", "ThreadLifecycleDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/Boot.java", "JavaBasicDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/operator/Boot.java", "OperatorDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/stream/Boot.java", "StreamBasicDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/variable/Boot.java", "VariableHidingDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/lambda/basic/Boot.java", "LambdaBasicDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/thread/Boot.java", "ThreadBasicDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/thread/local/Boot.java", "ThreadLocalDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/agent/basic/Boot.java", "JavaAgentDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/classloader/Boot.java", "ClassLoaderDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/gc/Boot.java", "GcTriggerDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/invoke/jsr292/Boot.java", "InvokeDynamicDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/memory/init/Boot.java", "InitOrderDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/memory/jol/Boot.java", "ObjectLayoutDemo"),
    # Main.java
    ("java-concurrency/src/main/java/manfred/exercises/concurrency/thread/pool/Main.java", "ThreadPoolDemo"),
    ("java-io/src/main/java/manfred/exercises/io/file/Main.java", "FileOperationDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/classloader/plugin/Main.java", "PluginLoaderDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/metaspace/Main.java", "MetaspaceDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/Main.java", "JitOptimizeDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/escape/Main.java", "EscapeAnalysisDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/omit/Main.java", "ExceptionStackOmitDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/init/Main.java", "ClassInitOrderDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/system/Main.java", "SystemPropertiesDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/dsl/Main.java", "DslStyleDemo"),
    ("low-level/src/main/java/manfred/exercises/lowlevel/computer/Main.java", "ComputerArchDemo"),
    ("java-net/src/main/java/manfred/exercises/net/Main.java", "JavaNetDemo"),
    ("java-serialization/src/main/java/manfred/exercises/serialization/jdk/Main.java", "JdkSerializationDemo"),
    ("java-serialization/src/main/java/manfred/exercises/serialization/messagepack/Main.java", "MessagePackDemo"),
    # X.java
    ("java-basics/src/main/java/manfred/exercises/lang/collection/X.java", "HashMapBucketDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/X.java", "JavaEvolutionDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/lambda/basic/X.java", "LambdaThreadSafeDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/memory/jol/X.java", "SampleDataObject"),
    # Basic.java
    ("java-basics/src/main/java/manfred/exercises/lang/thread/Basic.java", "ThreadExceptionDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/time/Basic.java", "TimeCalculationDemo"),
    # Test.java (non-JUnit)
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/reorder/Test.java", "InstructionReorderDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/reorder/ThreadTest.java", "ReorderThreadDemo"),
    # XxxTest → XxxDemo (src/main/java, no @Test annotation)
    ("java-concurrency/src/main/java/manfred/exercises/concurrency/forkjoin/ForkJoinPoolTest.java", "ForkJoinPoolDemo"),
    ("java-concurrency/src/main/java/manfred/exercises/concurrency/future/CompletableFutureTest.java", "CompletableFutureDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/invoke/LambdaTest.java", "LambdaBytecodeDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/invoke/MethodHandleTest.java", "MethodHandleDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/cache/CacheLineTest.java", "CacheLineDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/falsesharing/FalseShareTest.java", "FalseShareDemo"),
    ("jvm-all/src/main/java/manfred/exercises/jvm/optimize/falsesharing/FalseSharingTest.java", "FalseSharingDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/collection/DequeueTest.java", "DequeDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/other/ByteCodeTest.java", "BytecodeDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/other/NumberTest.java", "NumberDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/other/PrintfTest.java", "PrintfDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/other/RandTest.java", "RandomDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/regex/PatternTest.java", "RegexDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/time/DateTimeFormatterTest.java", "DateTimeFormatterDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/time/LocalDateTest.java", "LocalDateDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/time/TimeTest.java", "TimeApiDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/basic/util/BitSetTest.java", "BitSetDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/gc/WeakReferenceTest.java", "WeakReferenceDemo"),
    ("java-basics/src/main/java/manfred/exercises/lang/thread/YieldTest.java", "ThreadYieldDemo"),
    ("java-serialization/src/main/java/manfred/exercises/serialization/html/JsoupFileTest.java", "JsoupFileDemo"),
    ("java-serialization/src/main/java/manfred/exercises/serialization/html/JsoupHtmlTest.java", "JsoupHtmlDemo"),
    ("java-serialization/src/main/java/manfred/exercises/serialization/html/JsoupNetTest.java", "JsoupNetDemo"),
]

def rename_in_file(fpath, old_name, new_name):
    with open(fpath, encoding="utf-8", errors="replace") as f:
        content = f.read()
    # 替换 class/interface/enum 声明中的类名
    content = re.sub(
        r'\b(class|interface|enum)\s+' + re.escape(old_name) + r'\b',
        r'\1 ' + new_name,
        content
    )
    # 替换 new OldName(、OldName.class、OldName::、(OldName)
    content = re.sub(r'\b' + re.escape(old_name) + r'\b', new_name, content)
    with open(fpath, "w", encoding="utf-8") as f:
        f.write(content)

def rename_references_in_dir(src_root, old_name, new_name):
    """在整个模块 src 目录内替换对该类的引用"""
    for dirpath, _, files in os.walk(src_root):
        if "target" in dirpath:
            continue
        for fname in files:
            if not fname.endswith(".java"):
                continue
            fpath = os.path.join(dirpath, fname)
            with open(fpath, encoding="utf-8", errors="replace") as f:
                content = f.read()
            if re.search(r'\b' + re.escape(old_name) + r'\b', content):
                new_content = re.sub(r'\b' + re.escape(old_name) + r'\b', new_name, content)
                with open(fpath, "w", encoding="utf-8") as f:
                    f.write(new_content)

total = 0
for rel_path, new_name in RENAMES:
    old_path = os.path.join(ROOT, rel_path)
    if not os.path.exists(old_path):
        print(f"SKIP(not found): {rel_path}")
        continue

    old_name = os.path.basename(old_path).replace(".java", "")
    new_path = os.path.join(os.path.dirname(old_path), new_name + ".java")

    # 1. 修改文件内容（类名声明 + 自引用）
    rename_in_file(old_path, old_name, new_name)

    # 2. 重命名文件
    os.rename(old_path, new_path)

    # 3. 更新同模块内其他文件对该类的引用
    module_src = os.path.join(ROOT, rel_path.split("/src/")[0], "src")
    rename_references_in_dir(module_src, old_name, new_name)

    print(f"OK: {old_name} → {new_name}")
    total += 1

print(f"\n总计重命名: {total} 个类")
