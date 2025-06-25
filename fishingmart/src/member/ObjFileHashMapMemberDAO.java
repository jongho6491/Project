package member;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ObjFileHashMapMemberDAO extends HashMapMemberDAO implements FileMemberDB {

	private String dataFilename = DATA_FILE + ".obj";
	public ObjFileHashMapMemberDAO() {
		loadMembers();
	}
	
	@Override
	public void saveMembers() {
		try (
				FileOutputStream fos = new FileOutputStream(dataFilename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
			) {
			oos.writeObject(memberDB);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
	}
	@Override
	public void loadMembers() {
		try(
				FileInputStream fis = new FileInputStream(dataFilename);
				ObjectInputStream ois = new ObjectInputStream(fis);
			) {
				memberDB = (Map<String, MemberVO>)ois.readObject();
				for (MemberVO member : memberDB.values()) {
					memberSeq = member.getMemberNo() + 1;
				}
		} catch (FileNotFoundException e) {
			System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public boolean insertMember(MemberVO member) {
		boolean result = super.insertMember(member);
		if (result) saveMembers();
		return result;
	}
	
	@Override
	public boolean deleteMember(String id) {
	    if (memberDB.containsKey(id)) {
	        memberDB.remove(id);
	        saveMembers();  // 이 호출이 반드시 있어야 함!
	        return true;
	    }
	    return false;
	}
	
	@Override
	public boolean updateMember(MemberVO newMember) {
	    boolean result = super.updateMember(newMember);  // 기존 로직 호출 (put)
	    if (result) {
	        saveMembers();  // 파일에 저장
	    }
	    return result;
	}
}
