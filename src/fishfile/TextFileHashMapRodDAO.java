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

import rod.HashMapRodDAO;
import rod.RodVO;

public class TextFileHashMapRodDAO extends HashMapRodDAO implements FileRodDB {

	private String dataFilename = DATA_FILE + ".txt";
	private final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";
	
	@Override
	public void saveRods()  {
		try(
				FileWriter fw = new FileWriter(dataFilename);
				PrintWriter pw = new PrintWriter(fw);
			) {
				
				for (RodVO rod : rodDB.values()) {
					pw.println(rod.getRodNo());
					pw.println(rod.getName());
					pw.println(rod.getBrand());
					pw.println(rod.getLength());
					pw.println(rod.getWeight());
					pw.println(rod.getPrice());
					pw.println(rod.getInstock());
					
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					pw.println(sdf.format(rod.getRegdate()));
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void loadRods() {
		try (
				FileReader fr = new FileReader(dataFilename);
				BufferedReader br = new BufferedReader(fr);
			) {
			while(br.ready()) {
				int rodNo = Integer.parseInt(br.readLine());
				String name = br.readLine().strip();
				String brand = br.readLine().strip();
				int length = Integer.parseInt(br.readLine());
				int weigth = Integer.parseInt(br.readLine());
				int price = Integer.parseInt(br.readLine());
				int instock = Integer.parseInt(br.readLine());
				
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Date regdate = sdf.parse(br.readLine());
				
				rodDB.put(rodNo, new RodVO(rodNo, name, brand, length, weigth, price, instock, regdate));
				
				if (rodSeq <= rodNo) rodSeq = rodNo + 1;
			}
		} catch (FileNotFoundException e) {
			System.out.println("로딩 " + dataFilename + "이 없습니다");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

}
