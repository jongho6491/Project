package bait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Map;

public class ObjFileHashMapBaitDAO extends HashMapBaitDAO implements FileBaitDB {

	private String dataFilename = DATA_FILE + ".obj";
	
	public ObjFileHashMapBaitDAO() {
		loadBaits();
	}
	
	
	@Override
	public void saveBaits() {
		try (
			FileOutputStream fos = new FileOutputStream(dataFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			) {
			oos.writeObject(baitDB);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadBaits() {
		try (
			FileInputStream fis = new FileInputStream(dataFilename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			) {
			baitDB = (Map<Integer, BaitVO>)ois.readObject();
			baitSeq = Collections.max(baitDB.keySet()) + 1;
			
		} catch (FileNotFoundException e) {
			System.out.println("DB로딩 " + dataFilename + "가 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean insertBait(BaitVO bait) {
		boolean result = super.insertBait(bait);
		if (result) saveBaits();
		return result;
	}
	
	@Override
	public boolean updateBait(BaitVO bait) {
		boolean result = super.updateBait(bait);
		if (result) saveBaits();
		return result;
	}
	
	@Override
	public boolean deleteBait(int baitNo) {
		boolean result = super.deleteBait(baitNo);
		if (result) saveBaits();
		return result;
	}

}
