package manfred.exercises.serialization.json.gson;

/**
 * Gson 序列化练习中使用的简单用户数据模型。
 *
 * 作为 Gson 序列化的目标 POJO，包含姓名、年龄和邮箱地址字段，
 * 用于演示 Gson 如何将字段名直接映射为 JSON key，以及构造函数
 * 初始化对象后序列化的标准用法。
 */
public class User {
    //省略其它
    public String name;
    public int age;
    public String emailAddress;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}