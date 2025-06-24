package cart;

import java.util.List;

public class CartServiceImpl implements CartService {

	private CartDAO cartDAO;
	
	
	public CartServiceImpl(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}
	
	
	@Override
	public boolean addItem2Cart(CartItemVO item) {
		
		return cartDAO.insertCartItem(item);
	}

	@Override
	public CartItemVO getCartItemInfo(String itemKey) {
		
		return cartDAO.selectCartItem(itemKey);
	}

	@Override
	public List<CartItemVO> listCartItems() {
		
		return cartDAO.selectAllCartItems();
	}

	@Override
	public boolean isCartEmpty() {
		
		return cartDAO.selectAllCartItems().isEmpty();
	}

	@Override
	public boolean removeCartItem(String itemKey) {
		
		return cartDAO.deleteCartItem(itemKey);
	}

	@Override
	public boolean clearCart() {
		
		return cartDAO.clear();
	}
}
