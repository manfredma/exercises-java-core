package manfred.exercises.concurrency.forkjoin;

import manfred.exercises.concurrency.forkjoin.util.PrintTask;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * 演示 ForkJoinPool 提交无返回值任务（RecursiveAction）的基本用法。
 *
 * 通过 ForkJoinPool 提交 PrintTask 并等待执行完成，
 * 展示 Fork/Join 框架的任务提交与线程池生命周期管理，
 * 以及 awaitTermination 等待任务执行的使用方式。
 */
public class ForkJoinPoolDemo {

	public static void main(String[] args) throws Exception{
		testNoResultTask();
	}

	private static void testNoResultTask() throws InterruptedException{
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new PrintTask(1,50));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		pool.shutdown();
	}
}
