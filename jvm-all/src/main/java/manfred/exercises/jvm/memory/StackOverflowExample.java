package manfred.exercises.jvm.memory;

/**
 * 演示无限递归导致 JVM 栈溢出（java.lang.StackOverflowError）的触发。
 *
 * alwaysRecursion 方法无终止条件地递归调用自身，每次调用都会在线程栈上压入
 * 新的栈帧，最终耗尽线程栈空间并抛出 StackOverflowError，
 * 用于理解 JVM 线程栈深度限制（-Xss 参数）和递归调用的栈帧占用原理。
 */
public class StackOverflowExample {
    public static void main(String[] args) {
        alwaysRecursion(0);
    }

    public static void alwaysRecursion(int depth) {
        alwaysRecursion(depth + 1);
    }
}
