package cart;

import java.util.List;

public interface CartDAO {

	boolean insertCartItem(CartItemVO item);
	CartItemVO selectCartItem(String itemKey);
	List<CartItemVO> selectAllCartItems();
	boolean deleteCartItem(String itemKey);
	boolean clear();
}
