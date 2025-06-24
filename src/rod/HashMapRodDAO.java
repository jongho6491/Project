package rod;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapRodDAO implements RodDAO {

	protected Map<Integer, RodVO> rodDB = new HashMap<>();
	protected int rodSeq = 101;
	
	
	@Override
	public boolean insertRod(RodVO rod) {
		rod.setRodNo(rodSeq++);
		rod.setRegdate(new Date());
		if(rod == null) return false;
		rodDB.put(rod.getRodNo(), rod);
		return true;
	}

	@Override
	public RodVO selectRod(int rodNo) {
		
		return rodDB.get(rodNo);
	}

	@Override
	public List<RodVO> selectAllRods() {
		
		return new ArrayList<>(rodDB.values());
	}

	@Override
	public boolean updateRod(RodVO newRod) {
		
		return rodDB.put(newRod.getRodNo(), newRod) != null;
	}

	@Override
	public boolean deleteRod(int rodNo) {
		rodDB.remove(rodNo);
		if (rodDB.remove(rodNo) != null)
			return true;
		else
			return false;
	}

}
