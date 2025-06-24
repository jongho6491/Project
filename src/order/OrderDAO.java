package order;

import java.util.List;

public interface OrderDAO {
    boolean insertOrder(OrderVO order);
    OrderVO selectOrder(int orderNo); // 주문번호로 주문 전체 조회
    List<OrderVO> selectOrdersOfMember(String memberId);
    List<OrderVO> selectAllOrder();
}