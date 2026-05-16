package manfred.exercises.jvm.optimize;

/**
 * 演示 JVM 指令重排序在多线程场景下导致不符合直觉的执行结果。
 *
 * 一个线程按顺序写 a、b、c，另一个线程逆序读取，若出现 b==1 但 a==0 等异常状态，
 * 说明写操作被 JIT 或 CPU 重排序。通过大量重复执行提高触发概率，
 * 用于理解 happens-before 原则缺失时内存可见性和指令有序性问题。
 */
public class App {

    public static void main(String[] args) {

        for (int i = 0; i < 10_000_000; i++) {
            final State state = new State();

            // a = 0, b = 0, c = 0

            // Write values
            new Thread(() -> {
                shortWait(100000);
                state.a = 1;
                // a = 1, b = 0, c = 0
                state.b = 1;
                // a = 1, b = 1, c = 0
                state.c = state.a + 1;
                // a = 1, b = 1, c = 2
            }).start();

            // Read values - this should never happen, right?
            new Thread(() -> {
                // copy in reverse order so if we see some invalid state we know this is caused
                // by reordering and not by a race condition in reads/writes
                // we don't know if the reordered statements are the writes or reads (we will se
                // it is writes later)
                int tmpC = state.c;
                int tmpB = state.b;
                int tmpA = state.a;

                if (tmpB == 1 && tmpA == 0) {
                    System.out.println("Hey wtf!! b == 1 && a == 0 && c == " + tmpC);
                }
                if (tmpC == 2 && tmpB == 0) {
                    System.out.println("Hey wtf!! c == 2 && b == 0 && a == " + tmpA);
                }
                if (tmpC == 2 && tmpA == 0) {
                    System.out.println("Hey wtf!! c == 2 && a == 0 && b == " + tmpB);
                }
            }).start();

        }
        System.out.println("done");
    }

    static class State {
        int a = 0;
        int b = 0;
        int c = 0;
    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }

}