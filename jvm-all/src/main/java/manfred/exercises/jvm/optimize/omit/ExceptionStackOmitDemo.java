package manfred.exercises.jvm.optimize.omit;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;

/**
 * 演示 JIT 编译器对频繁抛出的异常进行栈轨迹优化（异常栈轨迹省略）的现象。
 *
 * 循环触发 NullPointerException，JIT 在达到编译阈值后会对同一位置反复抛出的异常
 * 进行优化，省略完整栈轨迹（getStackTrace().length == 0），
 * 用于理解 JVM JIT 编译对异常处理的优化策略及其对线上排查问题的潜在影响。
 */
public class ExceptionStackOmitDemo {

    private static final Logger LOGGER = getLogger(ExceptionStackOmitDemo.class);

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            try {
                count++; //统计调用次数
                error();
            } catch (Exception e) {
                if (e.getStackTrace().length == 0) {
                    LOGGER.info("no trace count:{}", count, e);
                    Thread.sleep(1000); //方便观察日志
                } else {
                    LOGGER.info("full trace count:{}", count, e);
                }
            }
        }
    }

    private static void error() {
        String nullMsg = null;
        nullMsg.toString();
    }

}
