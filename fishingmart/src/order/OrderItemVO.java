package order;

import java.io.Serializable;

import cart.Item;

public class OrderItemVO implements Serializable {
	private Item item;
	private int quantity;
	
	
	public OrderItemVO(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
		
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

    public int getTotalPrice() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "[상품: " + item.getName() +
               ", 번호: " + item.getItemNo() +
               ", 수량: " + quantity +
               ", 총액: " + getTotalPrice() + "원]";
    }
}
