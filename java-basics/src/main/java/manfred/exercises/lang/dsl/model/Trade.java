package manfred.exercises.lang.dsl.model;

/**
 * DSL 练习中的交易领域模型，表示一笔股票买入或卖出交易。
 *
 * 包含交易类型（BUY/SELL）、关联股票、数量和价格，
 * getValue() 返回数量与价格的乘积，
 * 作为 Order 的组成部分，由各种 DSL 构建器填充并组装。
 */
public class Trade {

    public enum Type {BUY, SELL}

    private Type type;

    private Stock stock;

    private int quantity;

    private double price;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public double getValue() {
        return quantity * price;
    }
}