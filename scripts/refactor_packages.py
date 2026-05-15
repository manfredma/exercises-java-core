#!/usr/bin/env python3
"""
批量重命名 Java 包：修改 package 声明、import 引用、移动文件到新目录
"""
import os
import re
import shutil

ROOT = "/Users/maxingfang/IdeaProjects/github/exercises-java-core"

# 格式：(模块相对路径, 旧包前缀, 新包前缀)
# 旧包前缀用 . 分隔，空字符串表示 default package
MAPPINGS = [
    # java-basic
    ("java-language/java-basic", "manfred.end.java.basic.string.intern", "manfred.exercises.lang.basic.string.intern"),
    ("java-language/java-basic", "manfred.end.java.basic.system.properties", "manfred.exercises.lang.basic.system"),
    ("java-language/java-basic", "manfred.end.java.basic.variable.hiding", "manfred.exercises.lang.basic.variable"),
    ("java-language/java-basic", "manfred.end.java.basic", "manfred.exercises.lang.basic"),

    # java-collection (default package)
    ("java-language/java-collection", "", "manfred.exercises.lang.collection"),

    # java-generic
    ("java-language/java-generic", "manfred.end.generic", "manfred.exercises.lang.generic"),

    # java-lambda
    ("java-language/java-lambda", "manfred.end.java.lambda.basic", "manfred.exercises.lang.lambda.basic"),
    ("java-language/java-lambda", "manfred.exercises.lang.lambda.functional", "manfred.exercises.lang.lambda.functional"),  # already correct from java8 migration
    ("java-language/java-lambda", "manfred.exercises.lang.lambda", "manfred.exercises.lang.lambda"),  # already correct

    # java-thread
    ("java-language/java-thread", "manfred.end.thread", "manfred.exercises.lang.thread"),

    # java-gc
    ("java-language/java-gc", "manfred.gc.ref", "manfred.exercises.lang.gc"),

    # jdk-concurrent
    ("java-language/jdk-concurrent", "concurrent", "manfred.exercises.concurrency"),

    # java-io-bio
    ("java-language/java-io/java-io-bio", "manfred.end.java.io.bio", "manfred.exercises.io.bio"),

    # java-io-nio
    ("java-language/java-io/java-io-nio", "manfred.end.java.io.nio.time.server", "manfred.exercises.io.nio.time"),
    ("java-language/java-io/java-io-nio", "manfred.end.java.io.nio", "manfred.exercises.io.nio"),

    # java-io-aio (default package)
    ("java-language/java-io/java-io-aio", "time.server", "manfred.exercises.io.aio"),

    # java-io-file (default package)
    ("java-language/java-io/java-io-file", "basic", "manfred.exercises.io.file"),

    # jdk-serialization
    ("java-language/java-serialization/jdk-serialization", "jdk.serialization", "manfred.exercises.serialization.jdk"),

    # json serialization
    ("java-language/java-serialization/json", "gson", "manfred.exercises.serialization.json.gson"),
    ("java-language/java-serialization/json", "jackson", "manfred.exercises.serialization.json.jackson"),
    ("java-language/java-serialization/json", "model", "manfred.exercises.serialization.json.model"),

    # html/jsonp
    ("java-language/java-serialization/html/jsonp", "manfred.end.jsonp", "manfred.exercises.serialization.html"),

    # proto-buf
    ("java-language/java-serialization/proto-buf", "manfred.proto.buf", "manfred.exercises.serialization.protobuf"),

    # concurrent/multi-task
    ("concurrent/multi-task", "manfred.multi.task", "manfred.exercises.concurrency.multitask"),

    # coroutine
    ("coroutine/ea-async", "ea.async.interview", "manfred.exercises.concurrency.coroutine.ea"),
    ("coroutine/quasar", "quasar", "manfred.exercises.concurrency.coroutine.quasar"),

    # jvm
    ("jvm/jvm-memory", "heap.oom", "manfred.exercises.jvm.memory.heap"),
    ("jvm/jvm-memory", "init.order", "manfred.exercises.jvm.memory.init"),
    ("jvm/jvm-memory", "meta.oom", "manfred.exercises.jvm.memory.meta"),
    ("jvm/jvm-memory", "out.heap.oom", "manfred.exercises.jvm.memory.offheap"),
    ("jvm/jvm-memory", "out.heap.study", "manfred.exercises.jvm.memory.offheap"),
    ("jvm/jvm-memory", "stack.oom", "manfred.exercises.jvm.memory.stack"),
    ("jvm/jvm-memory", "address", "manfred.exercises.jvm.memory.address"),
    ("jvm/jvm-memory", "jol", "manfred.exercises.jvm.memory.jol"),
    ("jvm/jvm-gc", "manfred.end.gc", "manfred.exercises.jvm.gc"),
    ("jvm/jvm-classloader", "manfred.end.clazz.loader", "manfred.exercises.jvm.classloader"),
    ("jvm/jvm-classloader", "manfred.end.plugin", "manfred.exercises.jvm.classloader.plugin"),
    ("jvm/jvm-proxy", "manfred.end.proxy.java.ex1", "manfred.exercises.jvm.proxy"),
    ("jvm/jvm-agent", "manfred.agent", "manfred.exercises.jvm.agent"),
    ("jvm/jvm-invoke", "kathik", "manfred.exercises.jvm.invoke"),
    ("jvm/jvm-invoke", "manfred.end.invoke", "manfred.exercises.jvm.invoke"),
    ("jvm/jvm-method-invoke", "manfred.end.jvm.method.invoke", "manfred.exercises.jvm.invoke.method"),
    ("jvm/jvm-off-heap", "manfred.end.jvm.off.heap.unsafe", "manfred.exercises.jvm.memory.unsafe"),
    ("jvm/jvm-optimize", "cache.line", "manfred.exercises.jvm.optimize.cache"),
    ("jvm/jvm-optimize", "escape", "manfred.exercises.jvm.optimize.escape"),
    ("jvm/jvm-optimize", "false_sharing", "manfred.exercises.jvm.optimize.falsesharing"),
    ("jvm/jvm-optimize", "inline", "manfred.exercises.jvm.optimize.inline"),
    ("jvm/jvm-optimize", "omit.exception.stack", "manfred.exercises.jvm.optimize.omit"),
    ("jvm/jvm-optimize", "out.order", "manfred.exercises.jvm.optimize.reorder"),
    ("jvm/jvm-metaspace", "manfred.end", "manfred.exercises.jvm.metaspace"),
    ("jvm/jvm-byte/byte-buddy", "manfred.end.jvm.bytebuddy", "manfred.exercises.jvm.bytecode.bytebuddy"),
    ("jvm/jvm-byte/javassist", "", "manfred.exercises.jvm.bytecode.javassist"),
    ("jvm/jvm-byte/jvm-byte-basic", "", "manfred.exercises.jvm.bytecode.basic"),
    ("jvm/jvm-native", "", "manfred.exercises.jvm.native_"),

    # net
    ("net/java-net", "manfred.end", "manfred.exercises.net"),

    # asm
    ("asm/compiler", "craft", "manfred.exercises.lowlevel.compiler"),

    # computer-organization-architecture
    ("computer-organization-architecture", "manfred.end", "manfred.exercises.lowlevel.computer"),
]

def pkg_to_path(pkg):
    return pkg.replace(".", "/")

def path_to_pkg(path):
    return path.replace("/", ".")

def refactor_module(module_rel, old_pkg, new_pkg):
    src_root = os.path.join(ROOT, module_rel, "src/main/java")
    if not os.path.exists(src_root):
        return 0

    count = 0
    for dirpath, dirs, files in os.walk(src_root):
        for fname in files:
            if not fname.endswith(".java"):
                continue
            fpath = os.path.join(dirpath, fname)
            with open(fpath, encoding="utf-8", errors="replace") as f:
                content = f.read()

            # 当前文件的包（从路径推导）
            rel = os.path.relpath(dirpath, src_root)
            if rel == ".":
                current_pkg = ""
            else:
                current_pkg = path_to_pkg(rel)

            # 判断此文件是否属于要处理的旧包
            if old_pkg == "":
                # default package：只处理 default package 的文件
                if current_pkg != "":
                    continue
            else:
                if current_pkg != old_pkg and not current_pkg.startswith(old_pkg + "."):
                    continue

            # 计算新包名
            if old_pkg == "":
                file_new_pkg = new_pkg
            elif current_pkg == old_pkg:
                file_new_pkg = new_pkg
            else:
                suffix = current_pkg[len(old_pkg):]  # 如 ".sub"
                file_new_pkg = new_pkg + suffix

            # 替换 package 声明
            new_content = content
            if old_pkg == "":
                # default package：在文件开头插入 package 声明
                if not re.match(r'\s*package\s+', content):
                    new_content = f"package {file_new_pkg};\n\n" + content
                else:
                    new_content = re.sub(
                        r'^(\s*)package\s+\S+\s*;',
                        f"\\1package {file_new_pkg};",
                        content, count=1, flags=re.MULTILINE
                    )
            else:
                new_content = re.sub(
                    r'^(\s*)package\s+' + re.escape(current_pkg) + r'\s*;',
                    f"\\1package {file_new_pkg};",
                    new_content, count=1, flags=re.MULTILINE
                )

            # 替换 import 中的旧包引用
            if old_pkg:
                new_content = re.sub(
                    r'import(\s+static\s+)?' + re.escape(old_pkg) + r'\b',
                    lambda m: f"import{m.group(1) or ' '}{new_pkg}" if m.group(1) else f"import {new_pkg}",
                    new_content
                )

            # 计算新文件路径
            new_dir = os.path.join(src_root, pkg_to_path(file_new_pkg))
            new_fpath = os.path.join(new_dir, fname)

            os.makedirs(new_dir, exist_ok=True)

            if new_fpath != fpath:
                # 写新路径
                with open(new_fpath, "w", encoding="utf-8") as f:
                    f.write(new_content)
                os.remove(fpath)
            else:
                with open(fpath, "w", encoding="utf-8") as f:
                    f.write(new_content)

            count += 1

    # 清理空目录
    for dirpath, dirs, files in os.walk(src_root, topdown=False):
        if dirpath == src_root:
            continue
        try:
            os.rmdir(dirpath)
        except OSError:
            pass

    return count

total = 0
errors = []
for module_rel, old_pkg, new_pkg in MAPPINGS:
    try:
        n = refactor_module(module_rel, old_pkg, new_pkg)
        if n > 0:
            print(f"OK  {n:3d} files  {module_rel}  {old_pkg or '(default)'} → {new_pkg}")
        total += n
    except Exception as e:
        errors.append(f"ERROR {module_rel} {old_pkg}: {e}")
        print(f"ERR {module_rel} {old_pkg}: {e}")

print(f"\n总计处理: {total} 个文件")
if errors:
    print("错误：")
    for e in errors:
        print(" ", e)
