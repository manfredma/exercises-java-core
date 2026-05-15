package manfred.exercises.jvm.optimize.reorder;

/**
 * 演示 JVM/CPU 指令重排序导致两个线程出现 x==0 且 y==0 的不可能结果。
 *
 * 理论上 thread one 执行 a=1; x=b，thread other 执行 b=1; y=a，
 * 若无重排序则 x 和 y 不可能同时为 0。当出现 (0,0) 时即说明发生了指令重排，
 * 用于直观验证在缺乏同步的情况下 JMM（Java 内存模型）不保证执行顺序。
 */
public class InstructionReorderDemo {
    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(new Runnable() {
                public void run() {
                    //由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
                    shortWait(100000);
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();other.start();
            one.join();other.join();
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }


    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }
}