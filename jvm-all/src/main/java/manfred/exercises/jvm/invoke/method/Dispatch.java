package manfred.exercises.jvm.invoke.method;

/**
 * 演示 JVM 方法分派中静态分派与动态分派共同作用的典型场景。
 *
 * Father 和 Son 各自重载了接受 QQ 和 _360 两种参数的方法，当静态类型为 Father
 * 但实际类型为 Son 时，参数类型在编译期静态确定而接收者在运行期动态确定，
 * 展示了 JVM invokevirtual 指令下方法重载与方法重写的交互行为。
 */
public class Dispatch {
    static class QQ{

    }
    static class _360{

    }

    public static class Father{
        public void hardChoice(QQ  arg){
            System.out.println("father choose qq");
        }
        public void hardChoice(_360  arg){
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father{
        public void hardChoice(QQ  arg){
            System.out.println("son choose qq");
        }
        public void hardChoice(_360  arg){
            System.out.println("son choose 360");
        }
    }
    public static void main(String[] args) throws Exception {
        new Dispatch().test();
    }

    public void test(){
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}