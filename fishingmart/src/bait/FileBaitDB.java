package bait;

public interface FileBaitDB {
	String DATA_FILE = "./data/baitDB";
	void saveBaits();
	void loadBaits();
}
