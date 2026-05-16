package manfred.exercises.lang.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ParentGeneric<T> {

}

class GenericSubClass extends ParentGeneric<String> {

}

class GenericSubClass2<T> extends ParentGeneric<T> {

}

/**
 * 演示通过反射在运行时获取泛型实际类型参数。
 *
 * 利用 Class.getGenericSuperclass() 与 ParameterizedType.getActualTypeArguments()
 * 获取子类固化的泛型类型，对比直接继承具体类型（SubClass）与保留泛型（SubClass2）、
 * 匿名子类（parentGeneric2）三种场景下能否成功获取实际类型参数的差异，
 * 帮助理解 Java 泛型擦除与类型令牌（Type Token）技巧。
 */
public class GenericGet {

    //获取实际的泛型类型
    public static <T> Type findGenericType(Class<T> cls) {
        Type genType = cls.getGenericSuperclass();
        Type finalNeedType = null;
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            finalNeedType = params[0];
        }
        return finalNeedType;
    }

    public static void main(String[] args) {
        // 1.
        GenericSubClass subClass = new GenericSubClass();
        GenericSubClass2<Integer> subClass2 = new GenericSubClass2<>();
        //打印 subClass 获取的泛型
        System.out.println("subClass: " + findGenericType(subClass.getClass()));
        //打印subClass2获取的泛型
        System.out.println("subClass2: " + findGenericType(subClass2.getClass()));

        // 2.
        ParentGeneric<String> parentGeneric1 = new ParentGeneric<String>();
        ParentGeneric<String> parentGeneric2 = new ParentGeneric<String>(){};

        //打印 parentGeneric1 获取的泛型
        System.out.println("parentGeneric1: " + findGenericType(parentGeneric1.getClass()));
        //打印 parentGeneric2 获取的泛型
        System.out.println("parentGeneric2: " + findGenericType(parentGeneric2.getClass()));

        // 3.
        System.out.println(subClass.getClass().getGenericSuperclass());
        Class<GenericSubClass> subKlass = GenericSubClass.class;
        System.out.println(subClass.getClass());
        System.out.println(subClass2.getClass().getGenericSuperclass());

        // 4.
        System.out.println(parentGeneric1.getClass().getGenericSuperclass());

    }
}

