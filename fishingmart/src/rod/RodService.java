package rod;

import java.util.List;

public interface RodService {

	boolean registRod(RodVO rod);
	List<RodVO> listRods();
	RodVO detailRodInfo(int rodNo);
	boolean updateRodPrice(int rodNo, int price);
	boolean updateRodInstock(int rodNo, int instock);
	boolean removeRod(int rodNo);
	RodVO getRod(int rodNo);
	
}
