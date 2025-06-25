package cart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ObjFileHashMapCartDAO extends HashMapCartDAO implements FileCartDB {

    private String dataFilename = DATA_FILE + ".obj";

    public ObjFileHashMapCartDAO() {
        loadCarts(); // 저장된 장바구니 로딩
    }

    @Override
    public void saveCarts() {
        try (
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilename))
        ) {
            oos.writeObject(cartDB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadCarts() {
        try (
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFilename))
        ) {
            cartDB = (Map<String, CartItemVO>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[DB 로딩] " + dataFilename + "이 없습니다.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertCartItem(CartItemVO item) {
    	String key = item.getItem().getItemKey();
        CartItemVO existing = cartDB.get(key);

        if (existing != null) {
            // 기존 수량에 새 수량을 더함
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            // 처음 담는 상품이라면 추가
            cartDB.put(key, item);
        }

        saveCarts();  // 파일 저장
        return true;
    }

    @Override
    public boolean deleteCartItem(String itemKey) {
        boolean result = super.deleteCartItem(itemKey);
        if (result) saveCarts();
        return result;
    }

    @Override
    public boolean clear() {
        boolean result = super.clear();
        if (result) saveCarts();
        return result;
    }
}