package manfred.exercises.jvm.agent;

import java.lang.instrument.Instrumentation;

/**
 * Java Agent 入口类，演示如何通过 premain 机制在 JVM 启动时注入字节码转换逻辑。
 *
 * 实现了 Java Instrumentation API 的 premain 方法，JVM 在加载目标应用前会优先执行
 * 此方法。通过向 Instrumentation 实例注册 ClassFileTransformer，可以拦截并修改
 * 所有类的字节码，是实现 APM 监控、热部署、AOP 增强等工具的基础机制。
 */
public class Agent {
    public static void premain(String args, Instrumentation inst) {
        new Throwable().printStackTrace(System.out);
        System.out.println("Hi, This is a agent!");
        //将类转换器添加到此`agent`的`instrumentation`实例之中
        inst.addTransformer(new TransformerAgentDemo());
    }
}
