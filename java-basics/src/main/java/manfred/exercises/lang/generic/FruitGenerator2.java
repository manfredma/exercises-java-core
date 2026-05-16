package manfred.exercises.lang.generic;

/**
 * 泛型接口 Generator 的具体类型实现示例。
 *
 * 演示在实现泛型接口时将类型参数固化为 String 的方式，
 * 与 FruitGenerator 形成对比：前者保留泛型，此处直接指定具体类型。
 */
class FruitGenerator2 implements Generator<String> {
    @Override
    public String next() {
        return null;
    }
}