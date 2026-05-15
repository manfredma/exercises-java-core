package manfred.exercises.jvm.bytecode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 演示使用 ByteBuddy 在运行时动态创建并加载新类。
 *
 * 通过 ByteBuddy API 创建 Object 的子类，拦截 toString 方法并返回固定字符串，
 * 随后加载生成的类并实例化调用，用于理解字节码增强框架动态生成类的基本流程。
 */
public class CreateClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello World ByteBuddy!"))
                .make();

        Class<?> dynamicType = unloadedType.load(CreateClass.class
                .getClassLoader())
                .getLoaded();

        System.out.println(dynamicType.newInstance().toString());
    }
}
