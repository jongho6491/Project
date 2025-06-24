package bait;

import java.util.List;

public interface BaitDAO {

	boolean insertBait(BaitVO bait);
	BaitVO selectBait(int baitNo);
	List<BaitVO> selectAllBaits();
	boolean updateBait(BaitVO newBait);
	boolean deleteBait(int baitNo);
}
