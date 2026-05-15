package manfred.exercises.serialization.json.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * 演示 Jackson 多态类型序列化与反序列化机制的动物园模型类。
 *
 * 通过在基类 {@code Animal} 上标注 {@code @JsonTypeInfo} 和 {@code @JsonSubTypes}，
 * 练习 Jackson 如何在序列化时将多态类型信息（如 {@code "type": "dog"}）写入 JSON，
 * 并在反序列化时根据类型标识自动还原为正确的子类实例（{@code Dog} 或 {@code Cat}）。
 */
public class Zoo {
    public Animal animal;

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public static class Animal {
        public String name;
    }

    @JsonTypeName("dog")
    public static class Dog extends Animal {
        public double barkVolume;
    }

    @JsonTypeName("cat")
    public static class Cat extends Animal {
        boolean likesCream;
        public int lives;
    }
}