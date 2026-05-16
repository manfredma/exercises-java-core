package manfred.exercises.jvm.bytecode;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 演示使用 Javassist 在运行时动态创建类并通过反射调用其方法。
 *
 * 通过 ClassPool 创建 CtClass，使用 CtNewMethod.make 以字符串形式编写方法体，
 * 最终将其编译为 Class 对象并实例化调用，展示了 Javassist 以源码字符串为输入
 * 操作字节码的核心能力，适合理解基于 Javassist 的代码生成框架原理。
 */
public class JavassistDemo {

    public static void main(String[] args) throws CannotCompileException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        //初始化Javassist的类池
        ClassPool classPool = ClassPool.getDefault();

        //创建一个HelloWorld类
        CtClass ctClass = classPool.makeClass("HelloWorld");

        //添加一个test方法，会打印Hello World，直接传入方法的字符串
        CtMethod ctMethod = CtNewMethod.make("" +
                "public static void test(){" +
                "    System.out.println(\"Hello World\");" +
                "}", ctClass);

        ctClass.addMethod(ctMethod);

        //生成类
        Class aClass = ctClass.toClass();

        //通过反射调用这个类的实例
        Object object = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("test", null);
        method.invoke(object, null);
    }
}