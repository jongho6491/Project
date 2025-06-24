package order;

import java.util.Date;
import java.util.List;

import bait.BaitService;
import bait.BaitVO;
import cart.Item;
import rod.RodService;
import rod.RodVO;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private RodService rodService;
    private BaitService baitService;

    private final int COMPLETE = 10;

    public OrderServiceImpl(OrderDAO orderDAO, RodService rodService, BaitService baitService) {
        this.orderDAO = orderDAO;
        this.rodService = rodService;
        this.baitService = baitService;
    }

    @Override
    public boolean orderItems(OrderVO order) {

        // 1. 주문 날짜 및 상태 설정
        order.setOrderDate(new Date());
        order.setStatus(COMPLETE);
        order.setDeliverDate(new Date());

        // 2. 주문 항목 순회
        for (OrderItemVO itemVO : order.getOrderItemList()) {
            Item item = itemVO.getItem();
            int quantity = itemVO.getQuantity();

            // 낚시대일 경우
            if (item instanceof RodVO rod) {
                int rodNo = rod.getRodNo();
                int instock = rodService.detailRodInfo(rodNo).getInstock();
                int newStock = instock - quantity;

                if (newStock >= 0) {
                    rodService.updateRodInstock(rodNo, newStock);
                } else {
                    System.out.println("[오류] 낚시대 재고 부족: " + rod.getName());
                    return false;
                }

            // 미끼일 경우
            } else if (item instanceof BaitVO bait) {
                int baitNo = bait.getBaitNo();
                int instock = baitService.detailBaitInfo(baitNo).getInstock();
                int newStock = instock - quantity;

                if (newStock >= 0) {
                    baitService.updateBaitInstock(baitNo, newStock);
                } else {
                    System.out.println("[오류] 미끼 재고 부족: " + bait.getName());
                    return false;
                }
            }
        }

        // 3. 주문 정보 저장
        orderDAO.insertOrder(order);
        return true;
    }

    @Override
    public List<OrderVO> listMyOrders(String memberId) {
        return orderDAO.selectOrdersOfMember(memberId);
    }

    @Override
    public List<OrderVO> listAllOrder() {
        return orderDAO.selectAllOrder();
    }
}