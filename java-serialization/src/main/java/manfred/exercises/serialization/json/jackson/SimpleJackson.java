package manfred.exercises.serialization.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import manfred.exercises.serialization.json.model.Customer;
/**
 * 演示 Jackson ObjectMapper 的基础序列化与反序列化操作。
 *
 * 练习 {@code ObjectMapper} 的核心用法：将 Java 对象序列化为 JSON 字符串
 * ({@code writeValueAsString})、将 JSON 字符串反序列化为对象 ({@code readValue})，
 * 以及通过 {@code JsonInclude.Include.NON_NULL} 配置控制空值字段的序列化行为。
 */
public class SimpleJackson {
    public static void main(String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String nullJson = objectMapper.writeValueAsString(null);
        if (nullJson != null && !nullJson.isEmpty()) {
            System.out.println("output: " + nullJson);
        }

        Customer customer = new Customer("manfred", 100);
        System.out.println(objectMapper.writeValueAsString(customer));

        System.out.println(objectMapper.readValue("null", String.class));
    }

}
