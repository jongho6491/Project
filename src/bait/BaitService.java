package bait;

import java.util.List;

public interface BaitService {

	boolean registBait(BaitVO bait);
	List<BaitVO> listBaits();
	BaitVO detailBaitInfo(int baitNo);
	boolean updateBaitPrice(int baitNo, int price);
	boolean updateBaitInstock(int baitNo, int instock);
	boolean removeBait(int baitNo);
	
}
