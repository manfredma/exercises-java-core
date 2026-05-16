package manfred.exercises.lang.dsl;

import static manfred.exercises.lang.dsl.MethodChainingOrderBuilder.forCustomer;
import static manfred.exercises.lang.dsl.NestedFunctionOrderBuilder.at;
import static manfred.exercises.lang.dsl.NestedFunctionOrderBuilder.buy;
import static manfred.exercises.lang.dsl.NestedFunctionOrderBuilder.on;
import static manfred.exercises.lang.dsl.NestedFunctionOrderBuilder.order;
import static manfred.exercises.lang.dsl.NestedFunctionOrderBuilder.sell;
import static manfred.exercises.lang.dsl.NestedFunctionOrderBuilder.stock;
import manfred.exercises.lang.dsl.model.Order;
import manfred.exercises.lang.dsl.model.Stock;
import manfred.exercises.lang.dsl.model.Trade;

/**
 * 对比演示构建订单对象的四种 DSL 风格。
 *
 * 包含原始的命令式写法（plain）、方法链（methodChaining）、
 * 嵌套函数（nestedFunction）和 Lambda（lambda）四种方式，
 * 直观展示各种 DSL 模式在可读性和代码量上的差异。
 */
public class DslStyleDemo {

    public void plain() {
        Order order = new Order();
        order.setCustomer( "BigBank" );

        Trade trade1 = new Trade();
        trade1.setType( Trade.Type.BUY );

        Stock stock1 = new Stock();
        stock1.setSymbol( "IBM" );
        stock1.setMarket( "NYSE" );

        trade1.setStock( stock1 );
        trade1.setPrice( 125.00 );
        trade1.setQuantity( 80 );
        order.addTrade( trade1 );

        Trade trade2 = new Trade();
        trade2.setType( Trade.Type.BUY );

        Stock stock2 = new Stock();
        stock2.setSymbol( "GOOGLE" );
        stock2.setMarket( "NASDAQ" );

        trade2.setStock( stock2 );
        trade2.setPrice( 375.00 );
        trade2.setQuantity( 50 );
        order.addTrade( trade2 );
    }

    public void methodChaining() {
        Order order = forCustomer( "BigBank" )
                        .buy( 80 ).stock( "IBM" ).on( "NYSE" ).at( 125.00 )
                        .sell( 50 ).stock( "GOOGLE" ).on( "NASDAQ" ).at( 375.00 )
                        .end();

    }

    public void nestedFunction() {
        Order order = order("BigBank",
                            buy(80,
                                stock( "IBM", on( "NYSE" ) ),
                                at(125.00)),
                            sell(50,
                                 stock("GOOGLE", on("NASDAQ")),
                                 at(375.00))
                           );
    }

    public void lambda() {
        Order order = LambdaOrderBuilder.order( o -> {
            o.forCustomer( "BigBank" );
            o.buy( t -> {
                t.quantity( 80 );
                t.price( 125.00 );
                t.stock( s -> {
                    s.symbol( "IBM" );
                    s.market( "NYSE" );
                } );
            });
            o.sell( t -> {
                t.quantity( 50 );
                t.price( 375.00 );
                t.stock( s -> {
                    s.symbol( "GOOGLE" );
                    s.market( "NASDAQ" );
                } );
            });
        } );
    }


    public static void main(String[] args) {
        DslStyleDemo demo = new DslStyleDemo();
        demo.plain();
        demo.methodChaining();
        demo.nestedFunction();
        demo.lambda();
    }

}
