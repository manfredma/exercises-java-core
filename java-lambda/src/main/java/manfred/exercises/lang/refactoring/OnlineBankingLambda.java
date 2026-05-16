package manfred.exercises.lang.refactoring;

import manfred.exercises.lang.refactoring.template.OnlineBanking;
import manfred.exercises.lang.lambda.model.Lambda;
import java.util.function.Consumer;

/**
 * 演示用 Lambda 替代模板方法模式的继承层次。
 *
 * processCustomer 接收 Consumer<Customer> 参数代替抽象方法，
 * 调用方直接传入 Lambda 表达式定义客户服务行为，无需子类继承，
 * 与 OnlineBanking 对比说明 Lambda 化简继承结构的效果。
 */
public class OnlineBankingLambda {

    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello!"));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    // dummy Customer class
    static private class Customer {}
    // dummy Database class
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }
}