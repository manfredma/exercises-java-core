package manfred.exercises.lang.dsl;

import manfred.exercises.lang.lambda.model.Lambda;
import manfred.exercises.lang.dsl.model.Order;
import manfred.exercises.lang.dsl.model.Stock;
import manfred.exercises.lang.dsl.model.Trade;

import java.util.stream.Stream;

/**
 * 使用嵌套函数（Nested Function）风格实现的订单 DSL 构建器。
 *
 * 通过静态工厂方法 order/buy/sell/stock/on/at 的嵌套调用描述订单结构，
 * 代码层级反映业务对象的嵌套关系，
 * 与方法链和 Lambda 风格形成对比，展示各自的可读性权衡。
 */
public class NestedFunctionOrderBuilder {

    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        order.setCustomer( customer );
        Stream.of(trades).forEach( order::addTrade );
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade( stock, price, Trade.Type.BUY );
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade( stock, price, Trade.Type.SELL );
    }

    private static Trade buildTrade( Stock stock, double price, Trade.Type buy ) {
        Trade trade = new Trade();
        trade.setType( buy );
        trade.setStock( stock );
        trade.setPrice( price );
        return trade;
    }

    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        Stock stock = new Stock();
        stock.setSymbol( symbol );
        stock.setMarket( market );
        return stock;
    }

    public static String on(String market) {
        return market;
    }
}