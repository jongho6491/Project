package cart;

public interface FileCartDB {
	String DATA_FILE = "./data/cartDB";
	void saveCarts();
	void loadCarts();
}
