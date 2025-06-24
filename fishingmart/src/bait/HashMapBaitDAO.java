package bait;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapBaitDAO implements BaitDAO {
	
	protected Map<Integer, BaitVO> baitDB = new HashMap<>();
	protected int baitSeq = 101;
	
	@Override
	public boolean insertBait(BaitVO bait) {
		bait.setBaitNo(baitSeq++);
		bait.setRegdate(new Date());
		baitDB.put(bait.getBaitNo(), bait);
		return true;
	}

	@Override
	public BaitVO selectBait(int baitNo) {
		return baitDB.get(baitNo);
	}

	@Override
	public List<BaitVO> selectAllBaits() {
		return new ArrayList<>(baitDB.values());
	}

	@Override
	public boolean updateBait(BaitVO newBait) {
		baitDB.put(newBait.getBaitNo(), newBait);
		return true;
	}

	@Override
	public boolean deleteBait(int baitNo) {
		
		return baitDB.remove(baitNo) != null;
	}

}
