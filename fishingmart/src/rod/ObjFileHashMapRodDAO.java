package rod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Map;

public class ObjFileHashMapRodDAO extends HashMapRodDAO implements FileRodDB {

	private String dataFilename = DATA_FILE + ".obj";
	
	public ObjFileHashMapRodDAO() {
		loadRods();
	}

	@Override
	public void saveRods() {
		try(
			FileOutputStream fos = new FileOutputStream(dataFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			) {
			oos.writeObject(rodDB);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadRods() {
		try(
			FileInputStream fis = new FileInputStream(dataFilename);
			ObjectInputStream ois = new ObjectInputStream(fis);	
			) {
			rodDB = (Map<Integer, RodVO>)ois.readObject();
			rodSeq = Collections.max(rodDB.keySet()) + 1;
			
		} catch (FileNotFoundException e) {
			System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean insertRod(RodVO rod) {
		boolean result = super.insertRod(rod);
		if (result) saveRods();
		return result;
	}
	
	@Override
	public boolean updateRod(RodVO rod) {
		boolean result = super.updateRod(rod);
		if (result) saveRods();
		return result;
	}
	
	@Override
	public boolean deleteRod(int rodNo) {
		boolean result = super.deleteRod(rodNo);
		if (result) saveRods();
		return result;
	}
}
