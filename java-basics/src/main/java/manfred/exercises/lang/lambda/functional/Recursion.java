package manfred.exercises.lang.lambda.functional;

import java.util.stream.LongStream;

/**
 * 演示阶乘计算的四种实现方式：迭代、普通递归、Stream 与尾递归。
 *
 * 对比 factorialIterative（循环）、factorialRecursive（普通递归）、
 * factorialStreams（LongStream.rangeClosed + reduce）
 * 与 factorialTailRecursive（尾递归辅助方法）的写法，
 * 说明 Java 不支持尾调用优化（TCE），尾递归版本在深度调用时仍会栈溢出。
 */
public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialStreams(5));
        System.out.println(factorialTailRecursive(5));
    }

    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r*=i;
        }
        return r;
    }

    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n*factorialRecursive(n-1);
    }

    public static long factorialStreams(long n){
        return LongStream.rangeClosed(1, n)
                         .reduce(1, (long a, long b) -> a * b);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }
}