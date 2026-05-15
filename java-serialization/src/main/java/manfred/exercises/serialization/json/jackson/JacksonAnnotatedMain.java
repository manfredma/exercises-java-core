package manfred.exercises.serialization.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import manfred.exercises.serialization.json.model.AnnotatedCustomer;
import manfred.exercises.serialization.json.model.Item;
import manfred.exercises.serialization.json.model.MyMixInForIgnoreType;
import manfred.exercises.serialization.json.model.User;
import manfred.exercises.serialization.json.model.Zoo;

/**
 * 演示 Jackson 注解体系对序列化行为的精细控制。
 *
 * 练习多种 Jackson 注解的实际效果，包括：
 * {@code @JsonPropertyOrder} 控制字段输出顺序、{@code @JsonRootName} 添加根节点包装、
 * {@code @JsonAnyGetter} 展开动态属性 Map、{@code @JsonGetter} 自定义字段名、
 * {@code @JsonRawValue} 嵌入原始 JSON 字符串、{@code @JsonValue} 枚举序列化，
 * 以及 MixIn 机制实现对第三方类的无侵入式注解配置。
 */
public class JacksonAnnotatedMain {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        // serializeJson();
        // serializationWithTypeInfo();
        serializeMixin();
    }

    private static void serializeJson() throws Exception {
        AnnotatedCustomer customer = new AnnotatedCustomer();
        customer.id = 1000;
        customer.name = "John Doe";
        customer.json = "{\"attr\":false}";
        Map<String, String> properties = customer.getProperties();
        properties.put("phone", "1234567890");
        properties.put("email", "qxfkt@example.com");

        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(customer));
    }

    private static void serializationWithTypeInfo() throws Exception {
        Zoo zoo1 = new Zoo();

        zoo1.animal = new Zoo.Dog();
        zoo1.animal.name = "Max";
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(zoo1));
    }

    private static void serializeMixin() throws Exception {
        Item item = new Item();
        item.id = 1;
        item.itemName = "Example Item";
        item.owner = new User();
        item.owner.name =  "John Doe";
        // System.out.println(MAPPER.writeValueAsString(item));
        MAPPER.addMixIn(User.class, MyMixInForIgnoreType.class);
        System.out.println(MAPPER.writeValueAsString(item));
    }

}
