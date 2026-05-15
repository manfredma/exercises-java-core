package manfred.exercises.serialization.json.model;

/**
 * 演示 Jackson 嵌套对象序列化的订单模型类。
 *
 * 包含关联 {@link Customer} 对象，用于练习 Jackson 对嵌套 POJO 的递归序列化行为，
 * 以及 {@code @JsonRootName} 等注解在嵌套结构中的传递与作用范围。
 */
public class Order {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
