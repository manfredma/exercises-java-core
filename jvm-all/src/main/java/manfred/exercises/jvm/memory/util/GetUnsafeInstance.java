package manfred.exercises.jvm.memory.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 通过反射获取 {@code sun.misc.Unsafe} 单例实例的工具类。
 *
 * {@code Unsafe} 无法通过构造器或 {@code getUnsafe()} 公开方法在普通代码中获取，
 * 此类利用反射访问其私有静态字段 {@code theUnsafe} 绕过限制，为需要直接内存操作、
 * CAS 原语、对象字段偏移量计算等底层功能的演示代码提供统一入口。
 */
public class GetUnsafeInstance {
    public static Unsafe getUnsafeInstance() {

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
