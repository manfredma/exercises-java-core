package manfred.exercises.lang.refactoring;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 演示工厂方法模式的传统 switch 实现与 Lambda Map 注册表两种写法。
 *
 * 传统方式在 createProduct 中用 switch 分支创建对象；
 * Lambda 方式将构造器引用存入 Map，通过 createProductLambda 查表实例化，
 * 消除 switch-case 样板代码，新增产品类型只需向 Map 注册一个条目。
 */
public class FactoryMain {

    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();

        Product p3 = ProductFactory.createProductLambda("loan");

    }

    static private class ProductFactory {
        public static Product createProduct(String name){
            switch(name){
                case "loan": return new Loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambda(String name){
            Supplier<Product> p = map.get(name);
            if(p != null) return p.get();
            throw new RuntimeException("No such product " + name);
        }
    }

    static private interface Product {}
    static private class Loan implements Product {}
    static private class Stock implements Product {}
    static private class Bond implements Product {}

    final static private Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}