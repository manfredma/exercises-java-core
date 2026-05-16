package manfred.exercises.jvm.proxy;

import manfred.exercises.jvm.proxy.impl.FunctionImpl;
import manfred.exercises.jvm.proxy.impl.FunctionHandler;
import java.lang.reflect.Proxy;
/**
 * JDK 动态代理演示的客户端入口类。
 *
 * 使用 Proxy.newProxyInstance 为 IFunction 接口创建动态代理对象，
 * 代理逻辑由 FunctionHandler 实现，调用目标方法时会打印方法名后委托给 FunctionImpl，
 * 用于理解 JDK 动态代理的创建流程和 InvocationHandler 的拦截机制。
 */
public class Client {

    public static void main(String[] args) {
        IFunction fun = (IFunction) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IFunction.class}, new FunctionHandler(new FunctionImpl()));
        try {
            fun.doSomething();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}