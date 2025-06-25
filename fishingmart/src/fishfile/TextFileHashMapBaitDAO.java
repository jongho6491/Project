package fishfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bait.BaitVO;
import bait.FileBaitDB;
import bait.HashMapBaitDAO;

public class TextFileHashMapBaitDAO extends HashMapBaitDAO implements FileBaitDB {

	private String dataFilename = DATA_FILE + ".obj";
	private final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";
	
	
	@Override
	public void saveBaits() {
		try (
			FileWriter fw = new FileWriter(dataFilename);
			PrintWriter pw = new PrintWriter(fw);
			) {
			for (BaitVO bait : baitDB.values()) {
				pw.println(bait.getBaitNo());
				pw.println(bait.getTitle());
				pw.println(bait.getName());
				pw.println(bait.getType());
				pw.println(bait.getWeight());
				pw.println(bait.getPrice());
				pw.println(bait.getInstock());
				
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				pw.println(sdf.format(bait.getRegdate()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void loadBaits() {
		try (
			FileReader fr = new FileReader(dataFilename);
			BufferedReader br = new BufferedReader(fr);
			) {
			while (br.ready()) {
				int baitNo = Integer.parseInt(br.readLine());
				String title = br.readLine().strip();
				String name = br.readLine().strip();
				String type = br.readLine().strip();
				int weight = Integer.parseInt(br.readLine());
				int price = Integer.parseInt(br.readLine());
				int instock = Integer.parseInt(br.readLine());
				
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Date regdate = sdf.parse(br.readLine());
				
				baitDB.put(baitNo, new BaitVO(baitNo, title, name, type, weight, price, instock, regdate));
				if (baitSeq <= baitNo) baitSeq = baitNo + 1;
			}
		} catch (FileNotFoundException e) {
			System.out.println("로딩 " + dataFilename + "가 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
