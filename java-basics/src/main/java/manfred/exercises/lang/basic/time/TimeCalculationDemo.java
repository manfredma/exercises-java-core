package manfred.exercises.lang.basic.time;

/**
 * 演示 Integer.MAX_VALUE 转换为天数的计算。
 *
 * 将 Integer.MAX_VALUE 毫秒数依次除以 1000、60、60、24，
 * 得出其对应的天数，帮助直观理解 int 最大值的时间量级。
 */
public class TimeCalculationDemo {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE/1000.0/60/60/24);
    }
}
