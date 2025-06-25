package cart;

public interface Item {

	int getItemNo();   // 상품 번호
	String getName();  // 상품 이름
	int getPrice();    // 상품 가격
	int getStock();
	String getItemKey(); // rod bait 구분 포함한 고유 key
}
