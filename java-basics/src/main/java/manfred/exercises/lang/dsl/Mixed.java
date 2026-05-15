package manfred.exercises.lang.dsl;

import static manfred.exercises.lang.dsl.MixedBuilder.forCustomer;
import static manfred.exercises.lang.dsl.MixedBuilder.buy;
import static manfred.exercises.lang.dsl.MixedBuilder.sell;
import manfred.exercises.lang.dsl.model.Order;



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
}