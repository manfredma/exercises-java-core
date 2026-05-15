package manfred.exercises.concurrency.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * 基于 RecursiveAction 实现的分治打印任务，演示 Fork/Join 框架的任务拆分与合并。
 *
 * 当任务范围超过阈值（THRESHOLD=9）时，将任务二分拆分并使用 invokeAll 并行执行；
 * 否则直接在当前线程顺序打印，展示 Fork/Join 分治递归思想和工作窃取调度机制。
 */
public class PrintTask extends RecursiveAction {

    private static final long serialVersionUID = 1L;

    private static final int THRESHOLD = 9;

    private int start;

    private int end;

    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        if (end - start < THRESHOLD) {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + ",i=" + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask firstTask = new PrintTask(start, middle);
            PrintTask secondTask = new PrintTask(middle + 1, end);
            invokeAll(firstTask, secondTask);
        }

    }
}