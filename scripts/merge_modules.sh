#!/bin/bash
set -e
ROOT="/Users/maxingfang/IdeaProjects/github/exercises-java-core"
cd "$ROOT"

# 合并 src 内容的函数
merge_src() {
  local src_module="$1"
  local dst_module="$2"
  local src_path="$ROOT/$src_module/src/main/java"
  local dst_path="$ROOT/$dst_module/src/main/java"
  if [ -d "$src_path" ]; then
    mkdir -p "$dst_path"
    cp -r "$src_path"/* "$dst_path"/ 2>/dev/null || true
    echo "  merged: $src_module/src/main/java → $dst_module/src/main/java"
  fi
  # 也合并 resources
  local src_res="$ROOT/$src_module/src/main/resources"
  local dst_res="$ROOT/$dst_module/src/main/resources"
  if [ -d "$src_res" ]; then
    mkdir -p "$dst_res"
    cp -r "$src_res"/* "$dst_res"/ 2>/dev/null || true
    echo "  merged: $src_module/src/main/resources"
  fi
}

echo "=== 1. 创建目标模块目录 ==="
for m in java-basics java-io java-serialization java-concurrency low-level; do
  mkdir -p "$ROOT/$m/src/main/java"
  mkdir -p "$ROOT/$m/src/test/java"
  echo "created: $m"
done

echo ""
echo "=== 2. 合并 java-basics ==="
for m in java-language/java-basic java-language/java-collection java-language/java-generic \
          java-language/java-lambda java-language/java-thread java-language/java-gc \
          java-language/java-jar; do
  merge_src "$m" "java-basics"
done

echo ""
echo "=== 3. 合并 java-io ==="
for m in java-language/java-io/java-io-bio java-language/java-io/java-io-nio \
          java-language/java-io/java-io-aio java-language/java-io/java-io-file; do
  merge_src "$m" "java-io"
done

echo ""
echo "=== 4. 合并 java-serialization ==="
for m in java-language/java-serialization/jdk-serialization \
          java-language/java-serialization/json \
          java-language/java-serialization/message-pack \
          java-language/java-serialization/proto-buf \
          java-language/java-serialization/html/jsonp; do
  merge_src "$m" "java-serialization"
done
# proto-buf 的 .proto 文件也复制
mkdir -p "$ROOT/java-serialization/src/main/proto"
cp "$ROOT/java-language/java-serialization/proto-buf/src/main/proto"/*.proto \
   "$ROOT/java-serialization/src/main/proto/" 2>/dev/null || true
echo "  merged: proto files"

echo ""
echo "=== 5. 合并 java-concurrency ==="
for m in java-language/jdk-concurrent concurrent/lock concurrent/multi-task \
          coroutine/quasar coroutine/ea-async; do
  merge_src "$m" "java-concurrency"
done

echo ""
echo "=== 6. 合并 low-level ==="
for m in asm/compiler asm/nasm computer-organization-architecture; do
  merge_src "$m" "low-level"
done

echo ""
echo "=== 完成 ==="
