package manfred.exercises.lang.basic.big;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 演示 BigDecimal 的精度控制与运算行为。
 *
 * 涵盖 setScale 设置精度与舍入模式、加减乘除运算后 scale 的变化规律，
 * 以及 BigDecimal("0.00") 与 BigDecimal("0") 在 scale 上的差异，
 * 帮助理解金融场景下精确数值计算的正确姿势。
 */
public class BigDecimalTests {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal(2).setScale(10, RoundingMode.DOWN);
        BigDecimal y = new BigDecimal("2.33333").setScale(11, RoundingMode.DOWN);
        System.out.println(x.add(y) + ":" + x.add(y).scale());
        System.out.println(x.subtract(y) + ":" + x.subtract(y).scale());
        System.out.println(x.multiply(y) + ":" + x.multiply(y).scale());
        System.out.println(x.divide(y, RoundingMode.DOWN) + ":" +
                x.divide(y, RoundingMode.DOWN).scale());
        System.out.println(x.divide(y, 12, RoundingMode.DOWN) + ":" +
                x.divide(y, 12, RoundingMode.DOWN).scale());

        System.out.println(new BigDecimal("0").scale());
        System.out.println(new BigDecimal("0.00").scale());
        System.out.println(new BigDecimal("0.0000").scale());

        System.out.println(new BigDecimal("20.00").divide(new BigDecimal("0.01")));
        System.out.println(new BigDecimal("2.0E+3").scale());

        System.out.println(new BigDecimal("0.00"));
        System.out.println(new BigDecimal("1111111111111111111111110000000000000000000.0000").scale());
        System.out.println(new BigDecimal("0.0000"));


    }
}
