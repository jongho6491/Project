package rod;

import java.io.Serializable;
import java.util.Date;

import cart.Item;

public class RodVO implements Item ,Serializable {

	private int rodNo;
	private String name;
	private String brand;
	private int length;
	private int weight;
	private int price;
	private int instock;
	private Date regdate;
	
	public RodVO(int rodNo, String name, String brand, int length, int weight, int price
				, int instock, Date regdate) {
		this.rodNo = rodNo;
		this.name = name;
		this.brand =brand;
		this.length = length;
		this.weight = weight;
		this.price = price;
		this.instock = instock;
		this.regdate = regdate;
	}
	
	public RodVO(String name, String brand, int length, int weight, int price, int instock) {
		this(-1, name, brand, length, weight, price, instock, null);
	}
	

	@Override
	public String toString() {
		return "[" + rodNo + ", " + name + ", " + brand + ", " + length + ", "
				+ weight + ", " + price + ", " + instock + regdate + "]";
	}

	public int getRodNo() {
		return rodNo;
	}

	public void setRodNo(int rodNo) {
		this.rodNo = rodNo;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInstock() {
		return instock;
	}

	public void setInstock(int instock) {
		this.instock = instock;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public int getItemNo() {
		
		return rodNo;
	}
	
	public String getItemKey() {
	    return "R" + getItemNo();  // 예: R101
	}
	
}
