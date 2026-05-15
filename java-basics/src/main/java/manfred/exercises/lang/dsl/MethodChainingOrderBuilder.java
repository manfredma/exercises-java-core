package manfred.exercises.lang.dsl;

import manfred.exercises.lang.dsl.model.Order;
import manfred.exercises.lang.dsl.model.Stock;
import manfred.exercises.lang.dsl.model.Trade;

/**
 * 使用方法链（Method Chaining）风格实现的订单 DSL 构建器。
 *
 * 每个构建步骤返回当前或下一阶段的 Builder 对象，
 * 通过链式调用 buy().stock().on().at() 完成订单组装，
 * 体现流式 API 的可读性优势与类型安全的分阶段构建模式。
 */
public class MethodChainingOrderBuilder {

    public final Order order = new Order();

    private MethodChainingOrderBuilder(String customer) {
        order.setCustomer( customer );
    }

    public static MethodChainingOrderBuilder forCustomer( String customer ) {
        return new MethodChainingOrderBuilder(customer);
    }

    public Order end() {
        return order;
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder( this, Trade.Type.BUY, quantity );
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder( this, Trade.Type.SELL, quantity );
    }

    private MethodChainingOrderBuilder addTrade(Trade trade) {
        order.addTrade( trade );
        return this;
    }

    public static class TradeBuilder {
        private final MethodChainingOrderBuilder builder;
        public final Trade trade = new Trade();

        private TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
            this.builder = builder;
            trade.setType( type );
            trade.setQuantity( quantity );
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder( builder, trade, symbol );
        }
    }

    public static class TradeBuilderWithStock {
        private final MethodChainingOrderBuilder builder;
        private final Trade trade;

        public TradeBuilderWithStock( MethodChainingOrderBuilder builder, Trade trade ) {
            this.builder = builder;
            this.trade = trade;
        }

        public MethodChainingOrderBuilder at(double price) {
            trade.setPrice( price );
            return builder.addTrade( trade );
        }
    }

    public static class StockBuilder {
        private final MethodChainingOrderBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol( symbol );
        }

        public TradeBuilderWithStock on(String market) {
            stock.setMarket( market );
            trade.setStock( stock );
            return new TradeBuilderWithStock( builder, trade );
        }
    }
}