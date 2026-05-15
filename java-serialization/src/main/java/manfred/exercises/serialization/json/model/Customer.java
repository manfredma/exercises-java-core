package manfred.exercises.serialization.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 演示 Jackson 注解对 POJO 字段名映射控制的客户模型类。
 *
 * 通过 {@code @JsonRootName} 为序列化结果添加根节点包装名称，
 * 通过 {@code @JsonProperty("x.age")} 将字段 {@code age} 映射为含特殊字符的
 * JSON key，展示 Jackson 对非标准字段命名的支持能力。
 */
@JsonRootName("xxx")
public class Customer {

    private String name;

    @JsonProperty("x.age")
    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
