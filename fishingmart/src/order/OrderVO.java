package order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderVO implements Serializable {

    public static final int STATUS_ORDERED = 0;       // 주문 완료
    public static final int STATUS_DELIVERING = 1;    // 배송 중
    public static final int STATUS_COMPLETED = 2;     // 배송 완료
    public static final int STATUS_CANCELED = 3;      // 주문 취소

    private int orderNo;
    private String memberId;
    private List<OrderItemVO> orderItemList;
    private int price;
    private String mobile;
    private String address;
    private Date orderDate;
    private int status;
    private Date deliverDate;

    // 생성자 1: 필수 정보만
    public OrderVO(String memberId, List<OrderItemVO> orderItemList, int price) {
        this.memberId = memberId;
        this.orderItemList = orderItemList;
        this.price = price;
        this.status = STATUS_ORDERED;
        this.orderDate = new Date();
    }

    // 생성자 2: 모든 정보 포함
    public OrderVO(int orderNo, String memberId, List<OrderItemVO> orderItemList,
                   String mobile, String address, Date orderDate, int status, Date deliverDate) {
        this.orderNo = orderNo;
        this.memberId = memberId;
        this.orderItemList = orderItemList;
        this.mobile = mobile;
        this.address = address;
        this.orderDate = orderDate;
        this.status = status;
        this.deliverDate = deliverDate;
    }

    public int getTotalPrice() {
        return orderItemList.stream()
                .mapToInt(OrderItemVO::getTotalPrice)
                .sum();
    }

    public String getStatusText() {
        return switch (status) {
            case STATUS_ORDERED -> "주문완료";
            case STATUS_DELIVERING -> "배송중";
            case STATUS_COMPLETED -> "배송완료";
            case STATUS_CANCELED -> "주문취소";
            default -> "알수없음";
        };
    }

    // Getter & Setter
    public int getOrderNo() {
    	return orderNo; 
    }
    
    public void setOrderNo(int orderNo) {
    	this.orderNo = orderNo; 
    }

    public String getMemberId() {
    	return memberId; 
    }
    
    public void setMemberId(String memberId) {
    	this.memberId = memberId; 
    }

    public List<OrderItemVO> getOrderItemList() {
    	return orderItemList; 
    }
    
    public void setOrderItemList(List<OrderItemVO> orderItemList) {
    	this.orderItemList = orderItemList; 
    }

    public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMobile() {
    	return mobile; 
    }
    
    public void setMobile(String mobile) {
    	this.mobile = mobile; 
    }

    public String getAddress() {
    	return address; 
    }
    
    public void setAddress(String address) {
    	this.address = address; 
    }

    public Date getOrderDate() {
    	return orderDate; 
    }
    
    public void setOrderDate(Date orderDate) {
    	this.orderDate = orderDate; 
    }

    public int getStatus() {
    	return status; 
    }
    
    public void setStatus(int status) {
    	this.status = status; 
    }

    public Date getDeliverDate() {
    	return deliverDate; 
    }
    
    public void setDeliverDate(Date deliverDate) {
    	this.deliverDate = deliverDate; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[주문번호: ").append(orderNo)
          .append(", 고객ID: ").append(memberId)
          .append(", 총액: ").append(getTotalPrice()).append("원");

        if (orderDate != null) {
            sb.append(", 주문일: ").append(orderDate)
              .append(", 상태: ").append(getStatusText());
            if (deliverDate != null) {
                sb.append(", 배송일: ").append(deliverDate);
            }
        }
        sb.append("]\n");

        sb.append("\t*** 배송처: ").append(address)
          .append(" (").append(mobile).append(")\n");

        for (OrderItemVO item : orderItemList) {
            sb.append(item).append("\n");
        }

        return sb.toString();
    }
}