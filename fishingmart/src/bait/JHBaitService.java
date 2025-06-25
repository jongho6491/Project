package bait;

import java.util.List;

public class JHBaitService implements BaitService {

	private BaitDAO baitDAO;
	
	public JHBaitService(BaitDAO baitDAO) {
		this.baitDAO = baitDAO;
	}
	
	@Override
	public boolean registBait(BaitVO bait) {
		
		return baitDAO.insertBait(bait);
	}

	@Override
	public List<BaitVO> listBaits() {
		
		return baitDAO.selectAllBaits();
	}

	@Override
	public BaitVO detailBaitInfo(int baitNo) {
		
		return baitDAO.selectBait(baitNo);
	}

	@Override
	public boolean updateBaitPrice(int baitNo, int price) {
		BaitVO bait = baitDAO.selectBait(baitNo);
		
		if (bait != null) {
			bait.setPrice(price);
			baitDAO.updateBait(bait);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateBaitInstock(int baitNo, int instock) {
		BaitVO bait = baitDAO.selectBait(baitNo);
		
		if (bait != null) {
			bait.setInstock(instock);
			baitDAO.updateBait(bait);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeBait(int baitNo) {
		BaitVO bait = baitDAO.selectBait(baitNo);
		
		if (bait != null) {
			baitDAO.deleteBait(baitNo);
			return true;
		}
		
		return false;
		
		// return baitDAO.deleteBait(baitNo);
	}

}
