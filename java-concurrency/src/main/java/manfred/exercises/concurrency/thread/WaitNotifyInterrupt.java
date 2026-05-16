package manfred.exercises.concurrency.thread;

/**
 * 演示 interrupt 中断处于 wait 等待状态的线程。
 *
 * 线程 A 进入 synchronized + obj.wait() 阻塞后，主线程调用 interrupt() 打断等待，
 * 线程 A 捕获 InterruptedException 并退出，展示 wait 与 interrupt 的交互机制。
 */
public class WaitNotifyInterrupt {
    static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        //创建线程
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("---begin---");
                    //阻塞当前线程
                    synchronized (obj) {
                      obj.wait();
                }
                    System.out.println("---end---");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("---begin interrupt threadA---");
        threadA.interrupt();
        System.out.println("---end interrupt threadA---");
    }
}