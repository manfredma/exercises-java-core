package manfred.exercises.jvm.bytecode;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 演示使用 ByteBuddy 为动态生成的类添加自定义方法和字段。
 *
 * 通过 ByteBuddy defineMethod 和 defineField API 动态为类添加公共方法（委托给 Bar）
 * 和公共字段，展示 ByteBuddy 在类结构层面进行字节码增强的能力。
 */
public class CreateMethodField {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException,
            InvocationTargetException, NoSuchFieldException {
        Class<?> type = new ByteBuddy()
                .subclass(Object.class)
                .name("MyClassName")
                .defineMethod("custom", String.class, Modifier.PUBLIC)
                .intercept(MethodDelegation.to(Bar.class))
                .defineField("x", String.class, Modifier.PUBLIC)
                .make()
                .load(CreateMethodField.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        Method m = type.getDeclaredMethod("custom", null);
        System.out.println(m.invoke(type.newInstance(), new Object[0]));
        System.out.println(type.getDeclaredField("x"));
    }

    public static class Bar {
        @BindingPriority(3)
        public static String sayHelloBar() {
            return "Holla in Bar!";
        }
    }
}
