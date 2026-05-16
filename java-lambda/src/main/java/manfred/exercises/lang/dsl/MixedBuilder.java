package manfred.exercises.lang.dsl;

import manfred.exercises.lang.lambda.model.Lambda;
import manfred.exercises.lang.dsl.model.Order;
import manfred.exercises.lang.dsl.model.Stock;
import manfred.exercises.lang.dsl.model.Trade;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 混合风格 DSL 的构建器实现，结合嵌套函数与 Lambda Consumer。
 *
 * forCustomer 接收 TradeBuilder 可变参数描述订单整体，
 * buy/sell 接收 Consumer&lt;TradeBuilder&gt; 描述单笔交易细节，
 * 内部 StockBuilder 通过方法链完成股票配置。
 */
public class MixedBuilder {

    public static Order forCustomer(String customer, TradeBuilder... builders) {
        Order order = new Order();
        order.setCustomer( customer );
        Stream.of(builders).forEach( b -> order.addTrade( b.trade ) );
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer) {
        return buildTrade( consumer, Trade.Type.BUY );
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer) {
        return buildTrade( consumer, Trade.Type.SELL );
    }

    private static TradeBuilder buildTrade( Consumer<TradeBuilder> consumer, Trade.Type buy ) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType( buy );
        consumer.accept( builder );
        return builder;
    }

    public static class TradeBuilder {
        private Trade trade = new Trade();

        public TradeBuilder quantity(int quantity) {
            trade.setQuantity( quantity );
            return this;
        }

        public TradeBuilder at(double price) {
            trade.setPrice( price );
            return this;
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder(this, trade, symbol);
        }
    }

    public static class StockBuilder {
        private final TradeBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol( symbol );
        }

        public TradeBuilder on(String market) {
            stock.setMarket( market );
            trade.setStock( stock );
            return builder;
        }
    }
}