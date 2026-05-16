package manfred.exercises.lang.functional;

import java.util.function.Supplier;
import java.util.function.Predicate;

/**
 * 演示函数式编程中的惰性列表（Lazy List）与无限序列。
 *
 * 通过 Supplier 延迟求值实现 LazyList，构造从 2 开始的无限整数序列，
 * 并结合埃拉托色尼筛法（Sieve of Eratosthenes）生成无限素数流，
 * 展示惰性求值避免无限序列立即计算的核心思想。
 */
public class LazyLists {

    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10,
                new Empty<Integer>()));

        System.out.println(l.head());

        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

        numbers = from(2);
        int prime_two = primes(numbers).head();
        int prime_three = primes(numbers).tail().head();
        int prime_five = primes(numbers).tail().tail().head();
        System.out.println(prime_two + " " + prime_three + " " + prime_five);

        // this will run until a stackoverflow occur because Java does not
        // support tail call elimination
        // printAll(primes(from(2)));
    }

    interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        MyList<T> filter(Predicate<T> p);
    }

    static class MyLinkedList<T> implements MyList<T> {
        final T head;
        final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new MyLinkedList<>(
                    head(), tail().filter(p)) : tail().filter(p);
        }
    }

    static class Empty<T> implements MyList<T> {
        public T head() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> filter(Predicate<T> p) {
            return this;
        }
    }

    static class LazyList<T> implements MyList<T> {
        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail.get();
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(),
                    () -> tail().filter(p)) : tail().filter(p);
        }

    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<Integer>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail()
                .filter(n -> n % numbers.head() != 0)));
    }

    static <T> void printAll(MyList<T> numbers) {
        if (numbers.isEmpty()) {
            return;
        }
        System.out.println(numbers.head());
        printAll(numbers.tail());
    }

}