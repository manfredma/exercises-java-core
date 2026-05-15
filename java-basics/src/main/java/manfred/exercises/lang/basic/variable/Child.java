package manfred.exercises.lang.basic.variable;

/**
 * 字段隐藏（field hiding）练习的子类。
 *
 * 定义了与父类同名的字段 x，用于演示子类字段对父类字段的隐藏效果，
 * 配合 JavaBasicDemo 类验证通过父类引用访问字段时的静态绑定行为。
 */
class Child extends Parent {
    String x = "child";
}
