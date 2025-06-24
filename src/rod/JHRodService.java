package rod;

import java.util.List;

public class JHRodService implements RodService {

	private RodDAO rodDAO;
	
	public JHRodService(RodDAO rodDAO) {
		this.rodDAO = rodDAO;
	}
	
	@Override
	public boolean registRod(RodVO rod) {
		
		return rodDAO.insertRod(rod);
	}

	@Override
	public List<RodVO> listRods() {
		
		return rodDAO.selectAllRods();
	}

	@Override
	public RodVO detailRodInfo(int rodNo) {
		
		return rodDAO.selectRod(rodNo);
	}

	@Override
	public boolean updateRodPrice(int rodNo, int price) {
		RodVO rod = rodDAO.selectRod(rodNo);
		if (rod != null) {
			rod.setPrice(price);
			rodDAO.updateRod(rod);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRodInstock(int rodNo, int instock) {
		RodVO rod = rodDAO.selectRod(rodNo);
		if (rod != null) {
			rod.setInstock(instock);
			rodDAO.updateRod(rod);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeRod(int rodNo) {
		RodVO rod = rodDAO.selectRod(rodNo);
		if (rod != null) {
			rodDAO.deleteRod(rodNo);
			return true;
		}
		return false;
	}

	@Override
	public RodVO getRod(int rodNo) {
		
		return rodDAO.selectRod(rodNo);
	}

}
