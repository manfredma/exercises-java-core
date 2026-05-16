package manfred.exercises.lang.collection;

import java.util.Deque;
import java.util.LinkedList;
/**
 * 演示 Deque（双端队列）的基本使用。
 *
 * 以 LinkedList 作为 Deque 的实现，演示 push（压栈至头部）操作
 * 以及 getFirst/getLast 访问首尾元素，理解双端队列的 LIFO 与 FIFO 行为差异。
 */
public class DequeDemo {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        deque.push(2);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
    }
}
