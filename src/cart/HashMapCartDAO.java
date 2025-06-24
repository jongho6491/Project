package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapCartDAO implements CartDAO {

	Map<String, CartItemVO> cartDB = new HashMap<>();

	@Override
	public boolean insertCartItem(CartItemVO item) {
		String key = item.getItem().getItemKey();  // Rod/Bait 고유 키
	    cartDB.put(key, item);  //  충돌 없이 저장
	    return true;
	}
	
	@Override
	public CartItemVO selectCartItem(String itemKey) {
		
		return cartDB.get(itemKey);
	}

	@Override
	public List<CartItemVO> selectAllCartItems() {
	
		return new ArrayList<>(cartDB.values());
	}

	@Override
	public boolean deleteCartItem(String itemKey) {
		
		return cartDB.remove(itemKey) != null;
	}

	@Override
	public boolean clear() {
		cartDB.clear();
		return true;
	}
}
