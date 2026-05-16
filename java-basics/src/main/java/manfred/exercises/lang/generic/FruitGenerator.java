package manfred.exercises.lang.generic;

/**
 * 泛型接口 Generator 的泛型实现示例。
 *
 * 演示通过保留类型参数 T 来实现泛型接口的方式，
 * 与 FruitGenerator2 形成对比：前者保留泛型，后者固化为具体类型。
 */
class FruitGenerator<T> implements Generator<T> {
    @Override
    public T next() {
        return null;
    }
}