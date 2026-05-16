package manfred.exercises.jvm.proxy.impl;

import manfred.exercises.jvm.proxy.IFunction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * JDK 动态代理的方法调用处理器，拦截目标接口的所有方法调用并添加日志。
 *
 * 实现 InvocationHandler 接口，在 invoke 方法中打印被调用的方法名，
 * 再通过反射将调用委托给真实的 FunctionImpl 对象，
 * 演示了 AOP 横切关注点（如日志、监控）的最基础实现方式。
 */
public class FunctionHandler implements InvocationHandler {

    private Object fun;

    public FunctionHandler(Object function) {
        this.fun = function;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("method=%s", method.getName()));
        return method.invoke(fun, args);
    }
}