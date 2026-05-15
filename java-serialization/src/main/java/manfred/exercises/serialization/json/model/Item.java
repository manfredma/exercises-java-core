package manfred.exercises.serialization.json.model;

/**
 * 演示 Jackson MixIn 机制中作为关联对象的商品模型类。
 *
 * 包含商品 ID、名称以及关联的 {@code User} 对象，配合 {@link JacksonAnnotatedMain}
 * 中的 MixIn 配置，演示如何通过 {@code @JsonIgnoreType} 在序列化时完整忽略
 * {@code owner} 字段所属的类型，而无需修改 {@code User} 类本身。
 */
public class Item {
    public int id;
    public String itemName;
    public User owner;
}