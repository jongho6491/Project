package order;

public interface FileOrderDB {

	String DATA_FILE = "./data/orderDB.obj";  // 경로 상수로 공유
    void loadOrders();   // 파일에서 주문정보 로딩
    void saveOrders();   // 파일로 주문정보 저장
}
