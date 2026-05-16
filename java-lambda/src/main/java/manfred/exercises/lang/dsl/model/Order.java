package manfred.exercises.lang.dsl.model;

import manfred.exercises.lang.dsl.builder.MethodChainingOrderBuilder;
import manfred.exercises.lang.dsl.builder.LambdaOrderBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * DSL 练习中的订单领域模型，表示一笔包含多个交易的客户订单。
 *
 * 持有客户名称和交易列表，提供 getValue() 汇总所有交易金额，
 * 作为 MethodChainingOrderBuilder、LambdaOrderBuilder 等各种 DSL 构建器的最终产物。
 */
public class Order {

    private String customer;

    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }
}