package manfred.exercises.serialization.json.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * 演示 Jackson MixIn 机制实现对目标类型的无侵入式注解配置。
 *
 * 该类作为 {@code User} 类的 MixIn 目标，通过标注 {@code @JsonIgnoreType} 注解，
 * 使 Jackson 在序列化包含 {@code User} 字段的对象时完整跳过该类型，
 * 无需修改 {@code User} 源码即可实现序列化行为的定制。
 */
@JsonIgnoreType
public class MyMixInForIgnoreType {}