package manfred.exercises.lang.basic.variable;

public class Boot {
    public static void main(String[] args) {
        Child child = new Child();
        Parent parent = child;
        System.out.println(parent.x);
        System.out.println(child.x);
    }

}
