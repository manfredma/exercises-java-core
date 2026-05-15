package manfred.exercises.jvm.memory.stack;

import java.util.Scanner;

/**
 * 演示通过不断创建新线程导致 JVM 栈内存溢出（OutOfMemoryError: unable to create new native thread）。
 *
 * 无限循环中持续创建新线程，每个线程阻塞在 Scanner.next() 占用操作系统线程栈内存，
 * 当系统无法再分配新的原生线程时抛出 OOM，用于区分线程数耗尽型 OOM 与
 * 单线程栈深度溢出（StackOverflowError）的不同触发场景。
 */
public class StackOom {
    public static void main(String[] args) {
        StackOom stackOom = new StackOom();
        while (true) {
            new Thread(stackOom::alwaysRecursion).start();
        }
    }

    private void alwaysRecursion() {
        new Scanner(System.in).next();
    }
}
