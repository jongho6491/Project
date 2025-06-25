package cart;

import java.util.List;

public interface CartService {

	boolean addItem2Cart(CartItemVO item);
	CartItemVO getCartItemInfo(String itemKey);
	List<CartItemVO> listCartItems();
	boolean isCartEmpty();
	boolean removeCartItem(String itemKey);
	boolean clearCart();
}
