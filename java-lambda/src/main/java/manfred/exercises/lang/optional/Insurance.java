package manfred.exercises.lang.optional;

/**
 * Optional 链式调用练习中的保险领域模型。
 *
 * 作为 Optional 链式调用末端对象，持有保险公司名称字段，
 * 配合 Person 和 Car 类演示多层 Optional 嵌套时 flatMap/map 的安全拆箱写法。
 */
public class Insurance {

    private String name;

    public String getName() {
        return name;
    }
}