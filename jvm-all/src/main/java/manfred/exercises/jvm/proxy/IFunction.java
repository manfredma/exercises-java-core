package manfred.exercises.jvm.proxy;
/**
 * JDK 动态代理演示中的目标接口定义。
 *
 * 声明单一抽象方法 doSomething，作为 Proxy.newProxyInstance 生成动态代理对象时
 * 所代理的接口，用于展示 JDK 动态代理对接口方法的拦截与委托机制。
 */
public interface IFunction {
    /**
     * 抽象函数接口
     *
     * @throws IllegalStateException
     */
    void doSomething() throws IllegalStateException;
}