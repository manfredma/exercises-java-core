package manfred.exercises.jvm.optimize;

/**
 * 演示 JIT 编译器逃逸分析优化的典型场景。
 *
 * {@code getAge()} 方法中创建的 {@code Student} 对象仅在方法内部使用，不会逃逸到
 * 堆上，JIT 可通过逃逸分析将其优化为栈上分配（Stack Allocation）或标量替换
 * （Scalar Replacement），从而消除 GC 压力。循环调用 200000 次可触发方法热编译，
 * 结合 {@code -XX:+PrintGC} 等 JVM 参数可观察 GC 次数的明显减少。
 */
public class EscapeAnalysisDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 200000; i++) {
            getAge();
        }
    }

    public static int getAge() {
        Student person = new Student(" 小明 ", 18);
        return person.getAge();
    }

    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
