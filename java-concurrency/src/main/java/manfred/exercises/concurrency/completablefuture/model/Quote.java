package manfred.exercises.concurrency.completablefuture.model;

/**
 * 表示商店返回的带折扣码的价格报价值对象。
 *
 * 封装商店名称、原始价格和折扣等级，提供 parse 工厂方法将
 * "shopName:price:discountCode" 格式字符串解析为 Quote 对象，
 * 作为 Shop → Quote → Discount 异步流水线的中间数据载体。
 */
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}
