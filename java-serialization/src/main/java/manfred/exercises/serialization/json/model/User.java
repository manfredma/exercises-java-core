package manfred.exercises.serialization.json.model;

/**
 * Jackson 序列化练习中作为关联对象使用的简单用户模型类。
 *
 * 作为 {@link Item} 的 owner 字段类型，配合 MixIn 机制演示
 * {@code @JsonIgnoreType} 对整个类型序列化的忽略效果，
 * 同时验证 Jackson 对公开字段（public field）的默认序列化处理方式。
 */
public class User {
    public String name;
}
