package manfred.exercises.jvm.proxy;
/**
 * IFunction 接口的真实实现类，用于 JDK 动态代理演示。
 *
 * doSomething 方法直接抛出 IllegalStateException，作为被代理的目标对象，
 * 展示代理层在目标方法抛出受检异常时的透传行为。
 */
public class FunctionImpl implements IFunction {
    @Override
    public void doSomething() throws IllegalStateException {
        // 方法什么也不做, 只抛异常
        throw new IllegalStateException();
    }
}