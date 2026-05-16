package manfred.exercises.jvm.bytecode;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * 演示使用 ByteBuddy 对已有类的方法进行拦截并委托给另一个类处理。
 *
 * 通过 MethodDelegation 机制将 Foo.sayHelloFoo 的调用委托给 Bar 中带有
 * @BindingPriority 注解的方法，体现了 ByteBuddy 实现 AOP 方法拦截的核心用法，
 * 同时演示了多个候选委托方法时优先级注解的作用。
 */
public class MethodInterceptor {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String r = new ByteBuddy()
                .subclass(Foo.class)
                .method(named("sayHelloFoo")
                        .and(isDeclaredBy(Foo.class)
                                .and(returns(String.class))))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(MethodInterceptor.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .sayHelloFoo();
        System.out.println(r);
    }

    public static class Foo {
        public String sayHelloFoo() {
            return "Hello in Foo!";
        }
    }

    public static class Bar {
        @BindingPriority(3)
        public static String sayHelloBar() {
            return "Holla in Bar!";
        }

        @BindingPriority(4)
        public static String sayBar() {
            return "bar！！！";
        }
    }
}

