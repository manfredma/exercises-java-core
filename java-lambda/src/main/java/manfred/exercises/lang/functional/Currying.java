package manfred.exercises.lang.functional;

import java.util.function.DoubleUnaryOperator;

/**
 * 演示函数式编程中的柯里化（Currying）技术。
 *
 * 通过 curriedConverter 和 expandedCurriedConverter 将双参数转换函数拆分为单参数函数，
 * 生成摄氏度转华氏度、美元转英镑、公里转英里等可复用的单参数转换器，
 * 展示柯里化如何从通用函数派生出领域专用函数。
 */
public class Currying {

    public static void main(String[] args) {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        System.out.println(convertCtoF.applyAsDouble(24));
        System.out.println(convertUSDtoGBP.applyAsDouble(100));
        System.out.println(convertKmtoMi.applyAsDouble(20));

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0/9, 0);
        System.out.println(convertFtoC.applyAsDouble(98.6));
    }

    static double converter(double x, double y, double z) {
        return x * y + z;
    }

    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }
}