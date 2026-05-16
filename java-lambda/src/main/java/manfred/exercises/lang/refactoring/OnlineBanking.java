package manfred.exercises.lang.refactoring;

/**
 * 演示模板方法模式：定义银行业务处理流程的抽象骨架。
 *
 * processCustomer 方法固定了"获取客户 + 处理客户"的流程骨架，
 * 子类通过实现 makeCustomerHappy 提供具体的客户服务逻辑，
 * 体现模板方法模式在不改变算法结构前提下扩展具体行为的设计意图。
 */
abstract class OnlineBanking {
    public void processCustomer(int id){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }
    abstract void makeCustomerHappy(Customer c);

    // dummy Customer class
    static private class Customer {}
    // dummy Datbase class
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }
}