package manfred.exercises.lang.functional;

import java.util.function.Function;

/**
 * 演示函数式编程中的函数组合（Combinators）模式。
 *
 * 实现通用的 compose 方法将两个函数 g、f 组合为 g(f(x))，
 * 以及 repeat 方法通过递归将一个函数重复应用 n 次，
 * 体现高阶函数在函数式编程中的组合能力。
 */
public class Combinators {

    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));
    }

    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }
}