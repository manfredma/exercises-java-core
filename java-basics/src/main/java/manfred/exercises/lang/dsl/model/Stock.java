package manfred.exercises.lang.dsl.model;

/**
 * DSL 练习中的股票领域模型，表示某交易所上市的股票。
 *
 * 包含股票代号（symbol）和所在交易所（market）两个属性，
 * 作为 Trade 的关联对象，由各种 DSL 构建器在描述交易时创建。
 */
public class Stock {

    private String symbol;

    private String market;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol( String symbol ) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket( String market ) {
        this.market = market;
    }
}