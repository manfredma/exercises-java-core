package manfred.exercises.lang.refactoring;

import java.util.*;

/**
 * 演示 Stream 调试场景：流中含 null 元素导致 NullPointerException。
 *
 * 在含有 null 的列表上执行 stream().map() 操作时会在运行时抛出异常，
 * 演示流操作中 null 值的陷阱及调试思路，提示使用 peek 或 filter(Objects::nonNull) 防范。
 */
public class Debugging{
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(p -> p.getX()).forEach(System.out::println);
    }

    private static class Point{
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}