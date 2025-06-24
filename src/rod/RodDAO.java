package rod;

import java.util.List;

public interface RodDAO {

	boolean insertRod(RodVO rod);
	RodVO selectRod(int rodNo);
	List<RodVO> selectAllRods();
	boolean updateRod(RodVO newRod);
	boolean deleteRod(int rodNo);
}
