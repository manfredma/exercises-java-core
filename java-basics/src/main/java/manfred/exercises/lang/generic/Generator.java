package manfred.exercises.lang.generic;

/**
 * 定义一个通用的泛型生成器接口。
 *
 * 只包含一个 next() 方法，用于返回类型参数 T 的实例，
 * 是练习泛型接口定义与实现的最小示例。
 */
public interface Generator<T> {
    public T next();
}