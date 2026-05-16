package manfred.exercises.lang.dsl.builder;

import manfred.exercises.lang.lambda.model.Lambda;
import manfred.exercises.lang.dsl.model.Order;
import manfred.exercises.lang.dsl.model.Stock;
import manfred.exercises.lang.dsl.model.Trade;

import java.util.function.Consumer;

/**
 * 使用 Lambda（函数式接口 Consumer）风格实现的订单 DSL 构建器。
 *
 * 通过嵌套的 Consumer 回调描述订单结构（客户、买卖交易、股票），
 * 与 MethodChainingOrderBuilder 和 NestedFunctionOrderBuilder 形成三种 DSL 风格对比，
 * 展示 Lambda 方式的简洁性与上下文传递能力。
 */
public class LambdaOrderBuilder {

    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept( builder );
        return builder.order;
    }

    public void forCustomer(String customer) {
        order.setCustomer( customer );
    }

    public void buy(Consumer<TradeBuilder> consumer) {
        trade( consumer, Trade.Type.BUY );
    }

    public void sell(Consumer<TradeBuilder> consumer) {
        trade( consumer, Trade.Type.SELL );
    }

    private void trade( Consumer<TradeBuilder> consumer, Trade.Type type ) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType( type );
        consumer.accept( builder );
        order.addTrade( builder.trade );
    }

    public static class TradeBuilder {
        private Trade trade = new Trade();

        public void quantity(int quantity) {
            trade.setQuantity( quantity );
        }

        public void price(double price) {
            trade.setPrice( price );
        }

        public void stock(Consumer<StockBuilder> consumer) {
            StockBuilder builder = new StockBuilder();
            consumer.accept( builder );
            trade.setStock( builder.stock );
        }
    }

    public static class StockBuilder {
        private Stock stock = new Stock();

        public void symbol(String symbol) {
            stock.setSymbol( symbol );
        }

        public void market(String market) {
            stock.setMarket( market );
        }
    }
}