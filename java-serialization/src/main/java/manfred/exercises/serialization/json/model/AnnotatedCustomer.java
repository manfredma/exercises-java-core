package manfred.exercises.serialization.json.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合运用多种 Jackson 序列化注解的客户信息模型类。
 *
 * 通过在字段和方法上标注 Jackson 注解，演示以下序列化定制能力：
 * {@code @JsonPropertyOrder} 控制属性输出顺序、{@code @JsonRootName} 设置根节点名称、
 * {@code @JsonRawValue} 嵌入原始 JSON 字符串、{@code @JsonAnyGetter} 将 Map 展开为
 * 顶层属性、{@code @JsonGetter} 自定义序列化后的字段名、{@code @JsonSerialize} 指定
 * 自定义序列化器，以及内部枚举使用 {@code @JsonValue} 控制序列化输出值。
 */
@JsonPropertyOrder({"properties", "name", "id"})
@JsonRootName(value = "customer")
public class AnnotatedCustomer {

    public int id;

    public String name;
    private Map<String, String> properties = new HashMap<>();

    @JsonRawValue
    public String json;

    public TypeEnumWithValue type = TypeEnumWithValue.TYPE2;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date eventDate = new Date();

    public Date birthDay = new Date();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonGetter("theName")
    public String getName() {
        return name;
    }

    public enum TypeEnumWithValue {
        TYPE1(1, "Type A"), TYPE2(2, "Type 2");

        private Integer id;
        private String name;

        TypeEnumWithValue(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }
    }


}
