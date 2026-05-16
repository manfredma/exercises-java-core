package manfred.exercises.lang.basic.model;

/**
 * 字段隐藏（field hiding）练习的父类。
 *
 * 定义字段 x 供子类隐藏，配合 JavaBasicDemo 类演示父类引用访问到的是父类字段，
 * 而子类引用访问到的是子类字段这一静态绑定特性。
 */
public class Parent {
	public String x = "parent";
}

