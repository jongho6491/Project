
package app;

import java.util.ArrayList;
import java.util.List;

import bait.BaitService;
import bait.BaitVO;
import bait.JHBaitService;
import bait.ObjFileHashMapBaitDAO;
import cart.CartItemVO;
import cart.CartService;
import cart.CartServiceImpl;
import cart.Item;
import cart.ObjFileHashMapCartDAO;
import member.JHMemberService;
import member.MemberService;
import member.MemberVO;
import member.ObjFileHashMapMemberDAO;
import order.ObjFileHashMapOrderDAO;
import order.OrderItemVO;
import order.OrderService;
import order.OrderServiceImpl;
import order.OrderVO;
import rod.JHRodService;
import rod.ObjFileHashMapRodDAO;
import rod.RodService;
import rod.RodVO;

public class FishingMartConsoleApp {
	
	String[] startMenuList = {"종료", "낚시대 목록", "미끼 목록", "로그인", "회원 가입"};
	String[] adminMenuList = {"로그아웃", "등록 낚시대 보기", "낚시대 등록", "낚시대 수정", "낚시대 삭제", "등록 미끼 보기", "미끼 등록", "미끼 수정", "미끼 삭제", "회원 목록", "주문 목록"};
	String[] memberMenuList = {"로그아웃", "낚시대 목록","낚시대 주문", "미끼 목록","미끼 주문", "주문목록보기", "장바구니 담기", "장바구니 보기", "내 정보"};
	String[] cartMenuList = {"돌아가기", "상품 주문", "상품 삭제", "장바구니 비우기"};
	String[] myInfoMenuList = {"돌아가기", "비밀번호 변경", "회원 탈퇴"};
	
	final String ADMIN_ID = "admin";
	final String ADMIN_PWD = "1234";
	final String ADMIN_NAME = "관리자";
	
	final String CONFIRM = "yes";
	
	RodService rs = new JHRodService(new ObjFileHashMapRodDAO());
	BaitService bs = new JHBaitService( new ObjFileHashMapBaitDAO());
	MemberService ms = new JHMemberService(new ObjFileHashMapMemberDAO());
	OrderService os = new OrderServiceImpl(new ObjFileHashMapOrderDAO(), rs, bs);
	CartService cs = new CartServiceImpl(new ObjFileHashMapCartDAO());
	MemberVO loggedMember;
	
	MyAppReader input = new MyAppReader();
	
	public static void main(String[] args) {
    	FishingMartConsoleApp app = new FishingMartConsoleApp();
    	app.displayWelcome();
    	app.controlStartMenu();
    }
	
	private void displayWelcome() {
		System.out.println("========================");
		System.out.println("========================");
		System.out.println(" 낚시Go에 오신걸 환영합니다.");
		System.out.println("========================");
		System.out.println("========================");
		
	}
	
	private void controlStartMenu() {
		int menu;
		do {
			menu = selectMenu(startMenuList);
			
			switch (menu) {
			case 1: menuRodList(); break;
			case 2: menuBaitList(); break;
			case 3: menuLogin(); break;
			case 4: menuSignUp(); break;
			case 0: menuExit(); break;
			default : menuWrongNumber();
			}	
		} while (menu != 0);
	}
	
	private void menuSignUp() {
		System.out.println("**** 회원 가입 ****");
		String id = input.readString(">> ID : ");
		String password = input.readString(">> PassWord : ");
		String username = input.readString(">> UserName : ");
		String mobile = input.readString(">> Mobile : ");
		String email = input.readString(">> Email : ");
		String address = input.readString(">> Address : ");
		
		if (ms.registMember(new MemberVO(id, password, username, mobile, email, address))) {
			System.out.println("회원 가입이 완료되었습니다. 로그인 하세요.");
		} else {
			System.out.println("회원 가입에 실패하였습니다.");
		}
	}
	private void menuWrongNumber() {
		System.out.println("없는 메뉴 입니다.");
	}
	
	private void menuExit() {
		System.out.println("낚시 GO 서비스를 종료합니다.\n감사합니다.");
	}
	
	private void menuRodList() {
		System.out.println("=== 낚시대 목록 입니다. ===");
		displayRodList();
	}
	
	private void displayRodList() {
		List<RodVO> rodList = rs.listRods();
		System.out.println("****************");
		if (rodList.isEmpty()) {
			System.out.println("등록된 낚시대가 없음.");
		} else {
			for (RodVO rod : rodList) {
				System.out.println(rod);
			}
		}
		System.out.println("***************");
	}
	
	private void menuBaitList() {
		System.out.println("=== 미끼 목록 입니다. ===");
		displayBaitList();
	}
	
	private void displayBaitList() {
		List<BaitVO> baitList = bs.listBaits();
		System.out.println("***************");
		if (baitList.isEmpty()) {
			System.out.println("등록된 미끼가 없음.");
		} else {
			for (BaitVO bait : baitList) {
				System.out.println(bait);
			}
		}
		System.out.println("***************");
	}
	
	private void menuLogin() {
		System.out.println("**** 로그인 ****");
		String id = input.readString(">> ID : ");
		String password = input.readString(">> PASSWORD : ");
		
		if (id.equals(ADMIN_ID) && password.equals(ADMIN_PWD)) {
	        loggedMember = new MemberVO(ADMIN_ID, ADMIN_PWD, ADMIN_NAME, "", "", "");
	        System.out.println("로그인 " + loggedMember.getUsername() + "님 환영합니다.");
	        controlAdminMent();
	        return;
	    }

	    // 일반 회원 로그인
	    MemberVO member = ms.login(id, password);  // ⬅️ MemberService에 login 메서드가 있어야 함
	    if (member != null) {
	        loggedMember = member;
	        System.out.println("로그인 " + loggedMember.getUsername() + "님 환영합니다.");
	        controlMemberMenu();
	    } else {
	        System.out.println("로그인을 하지 못했습니다.");
	    }
	}
	
	private void controlMemberMenu() {
		int menu;
		do {
			menu = selectMenu(memberMenuList);
			
			if(loggedMember == null) break;
			
			switch (menu) {
			case 1 : menuRodList(); break;
			case 2 : menuRodOrder(); break;
			case 3 : menuBaitList(); break;
			case 4 : menuBaitOrder(); break;
			case 5 : menuOrderList(); break;
			case 6 : menuAddItem2Cart(); break;
			case 7 : menuCartView(); break;
			case 8 : menuMyInfo(); break;
			case 0 : menuLogout(); break;
			default : menuWrongNumber();
			}
		} while (menu != 0);
	}
	
	private void menuRodOrder() {
		System.out.println("***** 낚시대 주문 *****");
		displayAvailableRodList();
		int rodNo = input.readInt(">> 낚시대상품 번호 : ");
		RodVO rod = rs.detailRodInfo(rodNo);
		
		if (rod == null) {
			System.out.println("없는 낚시대 입니다.");
			return;
		}
		
		int quantity = input.readInt(">> 주문량 (" + rod.getInstock() + "개 이내) : ");
		if (quantity > rod.getInstock()) {
			System.out.println("재고보다 많이 주문 했습니다.");
			return;
		}
		// 주문 낚시대 목록
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int price = rod.getPrice() * quantity;
		orderItemList.add(new OrderItemVO(rod, quantity));
		
		// 주문 정보 생성
		OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, price);
		
		// 배송 정보 추가
		setDeliveryInfo();
		order.setMobile(loggedMember.getMobile());
		order.setAddress(loggedMember.getAddress());
		
		if (os.orderItems(order)) {
			System.out.println("주문이 성공적으로 완료되었습니다.");
			System.out.println("배송이 완료되었습니다.");
		} else {
			System.out.println("주문을 실패했습니다.");
		}
	}
	
	private void menuBaitOrder() {
		System.out.println("***** 미끼 주문 *****");
		displayAvailableBaitList();
		int baitNo = input.readInt(">> 미끼상품 번호 : ");
		BaitVO bait = bs.detailBaitInfo(baitNo);
		
		if (bait == null) {
			System.out.println("없는 미끼 입니다.");
			return;
		}
		
		int quantity = input.readInt(">> 주문량 (" + bait.getInstock() + "권 이내) : ");
		if (quantity > bait.getInstock()) {
			System.out.println("주문량이 재고량보다 큽니다.");
			return;
		}
		
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int price = bait.getPrice() * quantity;
		orderItemList.add(new OrderItemVO(bait, quantity));
		
		OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, price);
		
		setDeliveryInfo();
		order.setMobile(loggedMember.getMobile());
		order.setAddress(loggedMember.getAddress());
		
		if (os.orderItems(order)) {
			System.out.println("주문이 성공적으로 완료되었습니다.");
			System.out.println("배송이 완료되었습니다.");
		} else {
			System.out.println("주문을 실패했습니다.");
		}
	}
	
	private void setDeliveryInfo() {
		if (loggedMember.getMobile() == null) {
			System.out.println("*** 배송 정보 입력하세요. ***");
			
			String mobile = input.readString(">> 모바일 번호 : ");
			String email = input.readString(">> 이메일 주소 : ");
			String address = input.readString(">> 주소 : ");
			
			loggedMember.setMobile(mobile);
			loggedMember.setEmail(email);
			loggedMember.setAddress(address);
			
			ms.addMemberInfo(loggedMember.getId(), mobile, email, address);
		}
	}
	
	private void displayAvailableRodList() {
		List<RodVO> rodList = rs.listRods();
		System.out.println("=================================");
		if (rodList.isEmpty()) {
			System.out.println("주문 가능한 낚시대가 없습니다.");
		} else {
			int count = 0;
			for (RodVO rod : rodList) {
				if (rod.getInstock() > 0) {
					System.out.println(rod);
					count++;
				}
			}
			if (count == 0) 
			System.out.println("주문 가능한 낚시대가 없습니다.");
		}
		System.out.println("================================");
	}
	
	private void displayAvailableBaitList() {
		List<BaitVO> baitList = bs.listBaits();
		System.out.println("=================================");
		if (baitList.isEmpty()) {
			System.out.println("주문 가능한 미끼가 없습니다.");
		} else {
			int count = 0;
			for (BaitVO bait : baitList) {
				if (bait.getInstock() > 0) {
					System.out.println(bait);
					count++;
				}
			}
			if (count == 0)
				System.out.println("주문 가능한 미끼가 없습니다.");
		}
		System.out.println("=================================");
	}
	
	private void menuAddItem2Cart() {
		System.out.println("***** 장바구니에 상품 담기 *****");

	    System.out.println("1. 낚시대 담기");
	    System.out.println("2. 미끼 담기");
	    int choice = input.readInt(">> 선택 > ");

	    Item selectedItem = null;

	    switch (choice) {
	        case 1 -> {
	            displayAvailableRodList();
	            int rodNo = input.readInt(">> 낚시대 상품 번호 : ");
	            RodVO rod = rs.detailRodInfo(rodNo);

	            if (rod == null) {
	                System.out.println("없는 낚시대입니다.");
	                return;
	            }

	            selectedItem = rod;
	        }
	        case 2 -> {
	            displayAvailableBaitList();
	            int baitNo = input.readInt(">> 미끼 상품 번호 : ");
	            BaitVO bait = bs.detailBaitInfo(baitNo);

	            if (bait == null) {
	                System.out.println("없는 미끼입니다.");
	                return;
	            }

	            selectedItem = bait;
	        }
	        default -> {
	            System.out.println("잘못된 선택입니다.");
	            return;
	        }
	    }

	    int quantity = input.readInt(">> 수량 입력 (" + selectedItem.getPrice() + "개 이내): ");

	    if (quantity <= 0 || (selectedItem instanceof RodVO rod && quantity > rod.getInstock())
	                     || (selectedItem instanceof BaitVO bait && quantity > bait.getInstock())) {
	        System.out.println("재고보다 많은 수량을 입력했습니다.");
	        return;
	    }

	    if (cs.getCartItemInfo(selectedItem.getItemKey()) == null) {
	        cs.addItem2Cart(new CartItemVO(selectedItem, quantity));
	        System.out.println("장바구니에 성공적으로 추가했습니다.");
	    } else {
	        System.out.println("이미 장바구니에 있는 상품입니다.");
	    }
	}
	
	private void menuCartView() {
		System.out.println("***** 장바구니 보기 *****");
		displayCartItemList();
		
		if (!cs.isCartEmpty()) controlCartMenu();
	}
	
	private void displayCartItemList() {
		if (cs.isCartEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");
		} else {
			System.out.println("============================");
			for (CartItemVO item : cs.listCartItems()) {
				System.out.println(item);
			}
			System.out.println("=============================");
		}
	}
	
	private void controlCartMenu() {
		int menu;
		do {
			menu = selectMenu(cartMenuList);
			switch(menu) {
			case 1 : menuCartOrder(); break;
			case 2 : menuCartItemDelete(); break;
			case 3 : menuCartClear(); break;
			case 0 : break;
			default : menuWrongNumber();
			}
		} while (menu != 0 && !cs.isCartEmpty());
	}
	
	private void menuCartOrder() {
		System.out.println("=== 장바구니 상품 주문 ===");
		displayCartItemList();
		
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int totalPrice = 0;
		for (CartItemVO item : cs.listCartItems()) {
		    CartItemVO item1 = cs.getCartItemInfo(item.getItem().getItemKey());  // ✅
		    int price = item.getItem().getPrice() * item.getQuantity();          // ✅
			 totalPrice += price;
			 orderItemList.add(new OrderItemVO(item.getItem(), item.getQuantity()));
		}
		
		OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, totalPrice);
		
		setDeliveryInfo();
		order.setMobile(loggedMember.getMobile());
		order.setAddress(loggedMember.getAddress());
		
		displayOrderInfo(order);
		
		String confirm = input.readString(">> 위와 같은 내용을 주문 및 결제 진행 하겠습니까? ('" + CONFIRM + "'이면 주문실행) : ");
		
		if (confirm.equals(CONFIRM)) {
			if (os.orderItems(order)) {
				System.out.println("주문이 완료되었습니다.");
				System.out.println("배송이 완료되었습니다.");
			} else {
				System.out.println("주문을 하지 못했습니다.");
			}
		}
	}
	
	private void displayOrderInfo(OrderVO order) {
		System.out.println(order);
	}
	
	private void menuCartItemDelete() {
		System.out.println("=== 장바구니 아이템 삭제 ===");
		displayCartItemList();
		String itemKey = input.readString(">>> 삭제할 상품 Key 입력 (예: R101, B105) : ");
		CartItemVO item = cs.getCartItemInfo(itemKey);
		if (item == null) {
		    System.out.println("없는 아이템 입니다.");
		} else {
		    cs.removeCartItem(itemKey);
		    System.out.println("장바구니에서 아이템 삭제완료.");
		}
		displayCartItemList();
	}
	
	private void menuCartClear() {
		System.out.println("=== 장바구니 ===");
		String confirm = input.readString(">> 장바구니의 모든 아이템을 삭제하겠습니까? ('" + CONFIRM + "'이면 삭제) : ");
		if (confirm.equals(CONFIRM)) {
			cs.clearCart();
			System.out.println("장바구니의 모든 아이템 삭제완료.");
		} else {
			System.out.println("장바구니 비우기가 취소되었습니다.");
		}
	}
	
	private void menuMyInfo() {
		System.out.println("**** 내 정보 ****");
		System.out.println(loggedMember);
		
		controlMyInfoMenu();
		
		if (loggedMember == null) {
		    // 로그인 전 상태로 이동
		    controlStartMenu();  // 메인 메뉴 다시 보여줌
		    return;
		}
	}
	
	private void controlMyInfoMenu() {
		int menu;
		do {
			menu = selectMenu(myInfoMenuList);
			
			switch (menu) {
			case 1 : menuUpdatePassword(); break;
			case 2 : menuMemberExit(); break;
			case 0 : break;
			default : menuWrongNumber();
			}
		} while (menu != 0 && loggedMember != null);
	}
	
	private void menuUpdatePassword() {
		System.out.println("**** 비밀번호 수정 ****");
		String oldPassword = input.readString(">> 기존 비밀번호 : ");
		String newPassword = input.readString(">> 새 비밀번호 : ");
		if (ms.updatePassword(loggedMember.getId(), oldPassword, newPassword)) {
			System.out.println("[비밀번호 수정] 비밀번호를 수정했습니다.");
		} else {
			System.out.println("[비밀번호 수정 실패] 비밀번호 수정에 실패했습니다.");
		}
	}
	
	private void menuMemberExit() {
		System.out.println("**** 회원 탈퇴 ****");
		String password = input.readString(">> 현재 비밀번호 : ");
		if (ms.removeMember(loggedMember.getId(), password)) {
			System.out.println("[회원 탈퇴] 회원정보, 주문정보를 삭제하였습니다. 그동안 서비스를 이용해 주셔서 감사합니다.");
			loggedMember = null;
		} else {
			System.out.println("[회원 탈퇴 실패] 회원 탈퇴 처리에 실패했습니다.");
		}
	}
	
	private void controlAdminMent() {
		int menu;
		do {
			menu = selectMenu(adminMenuList);
			
			switch (menu) {
			case 1 : menuRodList(); break;
			case 2 : menuRodRegist(); break;
			case 3 : menuRodUpdate(); break;
			case 4 : menuRodRemove(); break;
			case 5 : menuBaitList(); break;
			case 6 : menuBaitRegist(); break;
			case 7 : menuBaitUpdate(); break;
			case 8 : menuBaitRemove(); break;
			case 9 : menuMemberList(); break;
			case 10 : menuOrderList(); break;
			case 0 : menuLogout(); break;
			default : menuWrongNumber();
			}
		} while (menu != 0 && loggedMember != null);
	}
	
	private void menuRodRegist() {
		System.out.println("**** 낚시대 등록 ****");
		String name = input.readString(">> 낚시대 이름 : ");
		String brand = input.readString(">> 회사 : ");
		int length = input.readInt(">> 길이 : ");
		int weight = input.readInt(">> 무게 : ");
		int price = input.readInt(">> 가격 : ");
		int instock = input.readInt(">> 재고량 : ");
		
		if (rs.registRod(new RodVO(name, brand, length, weight, price, instock))) {
			System.out.println("낚시대를 등록했습니다.");
			displayRodList();
		} else {
			System.out.println("낚시대 등록에 실패했습니다.");
		}
	}
	
	private void menuBaitRegist() {
		System.out.println("**** 미끼 등록 ****");
		String title = input.readString(">> 미끼 회사 : ");
		String name = input.readString(">> 미끼 이름 : ");
		String type = input.readString(">> 미끼 타입 : ");
		int weight = input.readInt(">> 무게 : ");
		int price  = input.readInt(">> 가격 : ");
		int instock = input.readInt(">> 재고량 : ");
		
		if (bs.registBait(new BaitVO(title, name, type, weight, price, instock))) {
			System.out.println("미끼를 등록했습니다.");
			displayBaitList();
		} else {
			System.out.println("미끼 등록에 실패했습니다.");
		}
	}
	
	private void menuRodUpdate() {
		System.out.println("**** 낚시대 정보 수정 ****");
		displayRodList();
		int rodNo = input.readInt(">> 낚시대 번호 : ");
		
		int select = input.readInt(">> 수정할 정보 선택 (1. 가격 2. 재고량) : ");
		if (select == 1) {
			int price = input.readInt(">> 새 가격 : ");
			if (rs.updateRodPrice(rodNo, price)) {
				System.out.println("[낚시대 정보 수정] 가격을 수정하였습니다.");
			} else {
				System.out.println("[낚시대 정보 수정 오류] 없는 낚시대입니다.");
			}
		} else if (select == 2) {
			int instock = input.readInt(">> 새 재고량 : ");
			if (rs.updateRodInstock(rodNo, instock)) {
				System.out.println("[낚시대 정보 수정] 재고량을 수정하였습니다.");
			} else {
				System.out.println("[낚시대 정보 수정 오류] 없는 낚시대입니다.");
			}
		} else {
			System.out.println("[낚시대 정보 수정 취소] 지원하지 않는 기능입니다.");
		}
	}
	
	private void menuBaitUpdate() {
		System.out.println("**** 미끼 정보 수정 ****");
		displayBaitList();
		int baitNo = input.readInt(">> 미끼 번호 : ");
		
		int select = input.readInt(">> 수정할 정보 선택 (1. 가격 2. 재고량) : ");
		if (select == 1) {
			int price = input.readInt(">> 새 가격 : ");
			if (bs.updateBaitPrice(baitNo, price)) {
				System.out.println("[미끼 정보 수정] 가격을 수정하였습니다.");
			} else {
				System.out.println("[미끼 정보 수정 오류] 없는 미끼입니다.");
			}
		} else if (select == 2) {
			int instock = input.readInt(">> 새 재고량 : ");
			if (bs.updateBaitInstock(baitNo, instock)) {
				System.out.println("[미끼 정보 수정] 재고량을 수정하였습니다.");
			} else {
				System.out.println("[미끼 정보 수정 오류] 없는 미끼입니다.");
			}
		} else {
			System.out.println("[미끼 정보 수정 취소] 지원하지 않는 기능입니다.");
		}
	}
	
	private void menuRodRemove() {
		System.out.println("**** 낚시대 삭제 ****");
		displayRodList();
		int rodNo = input.readInt(">> 낚시대 번호 : ");
		String confirm = input.readString("선택한 낚시대를 삭제하시겠습니까? ('" + CONFIRM + "'를 입력하면 실행) : ");
		if (confirm.equals(CONFIRM)) {
			if (rs.removeRod(rodNo)) {
				System.out.println("[낚시대 삭제] 낚시대를 삭제했습니다.");
			} else {
				System.out.println("[낚시대 삭제 오류] 없는 낚시대입니다.");
			}
		} else {
			System.out.println("[낚시대 삭제 취소] 낚시대 삭제를 취소했습니다.");
		}
	}
	
	private void menuBaitRemove() {
		System.out.println("**** 미끼 삭제 ****");
		displayBaitList();
		int baitNo = input.readInt(">> 미끼 번호 : ");
		String confirm = input.readString("선택한 미끼를 삭제하시겠습니까? ('" + CONFIRM + "'를 입력하면 실행) : ");
		if (confirm.equals(CONFIRM)) {
			if (bs.removeBait(baitNo)) {
				System.out.println("[미끼 삭제] 미끼를 삭제했습니다.");
			} else {
				System.out.println("[미끼 삭제 오류] 없는 미끼입니다.");
			}
		} else {
			System.out.println("[미끼 삭제 취소] 미끼 삭제를 취소했습니다.");
		}
	}
	
	private void menuMemberList() {
		System.out.println("**** 회원 목록 ****");
		System.out.println("---------------------------------------");
		List<MemberVO> memberList = ms.listMembers();
		if (memberList.isEmpty()) {
			System.out.println("회원이 없습니다.");
		} else {
			for (MemberVO member : memberList) {
				System.out.println(member);
			}
		}
		System.out.println("---------------------------------------");
	}
	
	private void menuOrderList() {
		if (loggedMember.getId().equals(ADMIN_ID)) {
			System.out.println(os.listAllOrder());
		} else {
			System.out.println(os.listMyOrders(loggedMember.getId()));
		}
	}
	
	private void menuLogout() {
		
		System.out.println("[로그아웃] " + loggedMember.getUsername() + "님, 안녕히 가십시오.");
		loggedMember = null;
		
	}
	
	private int selectMenu(String[] menuList) {

		System.out.println("-------------------------------");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(i + ". " + menuList[i]);
		}
		System.out.println("0. " + menuList[0]);
		System.out.println("-------------------------------");
		return input.readInt(">> 메뉴 선택 : ");
	}
}
