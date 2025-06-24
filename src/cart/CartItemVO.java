package cart;

import java.io.Serializable;

public class CartItemVO implements Serializable{
	private Item item;
	private int quantity;
	
	public CartItemVO(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return item.getPrice() * quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
	    return "[상품: " + item.getName() + ", 수량: " + quantity + ", 총액: " + getTotalPrice() + "]";
	}

	
}
