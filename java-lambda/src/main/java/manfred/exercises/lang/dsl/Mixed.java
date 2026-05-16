package manfred.exercises.lang.dsl;

import manfred.exercises.lang.lambda.model.Lambda;
import static manfred.exercises.lang.dsl.builder.MixedBuilder.forCustomer;
import static manfred.exercises.lang.dsl.builder.MixedBuilder.buy;
import static manfred.exercises.lang.dsl.builder.MixedBuilder.sell;
import manfred.exercises.lang.dsl.model.Order;

/**
 * 演示混合风格 DSL：嵌套函数与 Lambda 结合构建订单。
 *
 * 外层使用嵌套函数（forCustomer、buy、sell）确定整体结构，
 * 内层使用 Lambda 回调描述交易细节，
 * 结合了嵌套函数的层次清晰与 Lambda 的灵活配置。
 */
public class Mixed {
    public void mixed() {
        Order order =
                forCustomer( "BigBank",
                             buy( t -> t.quantity( 80 )
                                        .stock( "IBM" )
                                        .on( "NYSE" )
                                        .at( 125.00 )),
                             sell( t -> t.quantity( 50 )
                                         .stock( "GOOGLE" )
                                         .on( "NASDAQ" )
                                         .at( 125.00 )) );

    }

    public static void main(String[] args) {
        new Mixed().mixed();
    }

}
