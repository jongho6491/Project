package bait;

import java.io.Serializable;
import java.util.Date;

import cart.Item;

public class BaitVO implements Item, Serializable {

	private int baitNo;
	private String title;
	private String name;
	private String type;
	private int weight;
	private int price;
	private int instock;
	private Date regdate;
	
	public BaitVO(int baitNo, String title, String name, String type, int weight
				  , int price, int instock, Date regdate) {
		this.baitNo = baitNo;
		this.title = title;
		this.name = name;
		this.type = type;
		this.weight = weight;
		this.price = price;
		this.instock = instock;
		this.regdate = regdate;
	}
	
	public BaitVO(String title, String name, String type, int weight, int price, int instock) {
		this(-1, title, name, type, weight, price, instock, null);
	}
	
	@Override
	public String toString() {
		return "[번호: " + baitNo +
		           ", 상품명: " + title +
		           ", 브랜드: " + name +
		           ", 종류: " + type +
		           ", 무게: " + weight + "g" +
		           ", 가격: " + price + "원" +
		           ", 재고: " + instock + "개" +
		           ", 등록일: " + regdate +
		           "]";
	}

	public int getBaitNo() {
		return baitNo;
	}
	
	public void setBaitNo(int baitNo) {
		this.baitNo = baitNo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
		
		return baitNo;
	}
	
	@Override
	public String getItemKey() {
	    return "B" + getItemNo();  // 예: B101
	}

	@Override
	public int getStock() {
		
		return instock;
	}
	
}
