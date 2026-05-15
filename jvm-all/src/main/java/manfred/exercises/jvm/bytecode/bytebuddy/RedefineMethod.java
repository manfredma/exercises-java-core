package manfred.exercises.jvm.bytecode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * 演示使用 ByteBuddy 结合 Java Agent 对已加载类的方法进行热重定义。
 *
 * 通过 ByteBuddyAgent.install() 安装代理，再使用 ClassReloadingStrategy 将
 * Foo.sayHelloFoo 的实现替换为返回固定字符串，展示了在不重启 JVM 的前提下
 * 修改已有类行为的热部署能力，是理解 Java Instrumentation redefineClasses 的实践演示。
 */
public class RedefineMethod {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Foo.class)
                .method(named("sayHelloFoo"))
                .intercept(FixedValue.value("Hello Foo Redefined"))
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        Foo f = new Foo();

        System.out.println(f.sayHelloFoo());
    }

    public static class Foo {
        public String sayHelloFoo() {
            return "Hello in Foo!";
        }
    }

}


