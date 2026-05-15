package manfred.exercises.lang.stream;

/**
 * Stream 综合练习中的交易记录领域模型。
 *
 * 持有交易员、年份和金额三个字段，与 Trader 类共同构成练习数据，
 * 用于 PuttingIntoPractice 中演示 Stream 过滤、排序、聚合等综合操作。
 */
public class Transaction{

	private Trader trader;
	private int year;
	private int value;

	public Transaction(Trader trader, int year, int value)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader(){ 
		return this.trader;
	}

	public int getYear(){
		return this.year;
	}

	public int getValue(){
		return this.value;
	}
	
	public String toString(){
	    return "{" + this.trader + ", " +
	           "year: "+this.year+", " +
	           "value:" + this.value +"}";
	}
}