package manfred.exercises.serialization.json.gson;

import com.google.gson.Gson;
/**
 * 演示 Gson 库的基本序列化与反序列化操作。
 *
 * 练习使用 {@code com.google.gson.Gson} 对基本数据类型（int、double、boolean、String）
 * 及自定义 Java 对象进行 JSON 序列化（{@code toJson}）和反序列化（{@code fromJson}），
 * 展示 Gson 零配置的开箱即用特性。
 */
public class SimpleGson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        int i = gson.fromJson("100", int.class);              //100
        double d = gson.fromJson("\"99.99\"", double.class);  //99.99
        boolean b = gson.fromJson("true", boolean.class);     // true
        String str = gson.fromJson("String", String.class);   // String

        User user = new User("怪盗kidou",24);
        String jsonObject = gson.toJson(user); // {"name":"怪盗kidou","age":24}
        System.out.println(jsonObject);
    }
}
