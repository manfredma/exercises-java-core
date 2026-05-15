package manfred.exercises.lang.stream;
/**
 * Stream 综合练习中的交易员领域模型。
 *
 * 持有姓名和所在城市两个字段，配合 Transaction 类构成交易数据集，
 * 用于 PuttingIntoPractice 中演示多种 Stream 查询操作。
 */
public  class Trader{
	
	private String name;
	private String city;

	public Trader(String n, String c){
		this.name = n;
		this.city = c;
	}

	public String getName(){
		return this.name;
	}

	public String getCity(){
		return this.city;
	}

	public void setCity(String newCity){
		this.city = newCity;
	}

	public String toString(){
		return "Trader:"+this.name + " in " + this.city;
	}
}