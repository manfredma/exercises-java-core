package manfred.exercises.lang.dsl.model;

/**
 * DSL 练习中的税率计算工具类，提供地区税、一般税和附加税三种静态计算方法。
 *
 * 每种税率以乘数形式作用于价值，供 TaxCalculator 以函数引用方式组合使用，
 * 演示将业务规则封装为可复用函数的设计思路。
 */
public class Tax {
    public static double regional(double value) {
        return value * 1.1;
    }

    public static double general(double value) {
        return value * 1.3;
    }

    public static double surcharge(double value) {
        return value * 1.05;
    }
}