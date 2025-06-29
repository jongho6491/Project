# 낚시 쇼핑 콘솔 애플리케이션 - FishingMartConsoleApp

FishingMartConsoleApp은 자바 콘솔 환경에서 동작하는 낚시용품 전문 쇼핑 애플리케이션입니다.  
회원가입부터 상품 탐색, 장바구니, 주문 및 배송 정보 관리까지 실제 쇼핑몰 흐름을 반영한 구조로 구현되어 있습니다.  
낚시대와 미끼 상품을 중심으로 사용자는 손쉽게 상품을 주문할 수 있으며,  
관리자는 상품과 주문을 효율적으로 관리할 수 있는 기능도 제공합니다.

---

##  프로젝트 개요

- 콘솔 기반 자바 애플리케이션으로, 객체지향 구조와 파일 저장 방식을 활용한 쇼핑 시스템입니다.
- 사용자/관리자 모드를 지원하며, 회원 관리부터 주문/배송까지의 흐름을 구현하였습니다.

---

##  주요 기능

**▶ 사용자 기능**
- 회원가입 / 로그인 / 내 정보 보기  
- 낚시대 목록 조회 및 주문  
- 미끼 목록 조회 및 주문  
- 장바구니 담기 / 보기  
- 주문 내역 확인 (배송지 포함)  
- 배송지 및 연락처 변경 가능  

**▶ 관리자 기능**
- 낚시대 및 미끼 상품 등록, 수정, 삭제  
- 전체 주문 내역 확인  

---
##  패키지 구조

```
src/
├── app/
│   ├── FishingMartConsoleApp.java       // 메인 실행 클래스
│   └── MyAppReader.java                 // 사용자 입력 처리 클래스
│
├── bait/                                // 미끼 관련 VO, DAO, Service
├── cart/                                // 장바구니 관련 VO, DAO, Service
├── fishfile/                            // 텍스트 기반 DAO 구현체
├── member/                              // 회원 관련 VO, DAO, Service
├── order/                               // 주문 관련 VO, DAO, Service
└── rod/                                 // 낚시대 관련 VO, DAO, Service
```


---

##  주요 클래스 설명

| 패키지    | 클래스명                                                   | 설명                    |
|-----------|------------------------------------------------------------|-------------------------|
| app       | `FishingMartConsoleApp`, `MyAppReader`                     | 메인 실행 및 입력 유틸      |
| bait      | `BaitVO`, `BaitDAO`, `BaitService` 등                       | 미끼 상품 관련 처리       |
| cart      | `CartItemVO`, `CartServiceImpl`, `HashMapCartDAO` 등       | 장바구니 기능 처리        |
| member    | `MemberVO`, `MemberService`, `MemberDAO` 등                 | 회원 정보 관리 처리       |
| order     | `OrderVO`, `OrderItemVO`, `OrderService`, `OrderDAO` 등     | 주문 및 주문 상세 처리     |
| rod       | `RodVO`, `RodService`, `RodDAO` 등                          | 낚시대 상품 관련 처리      |
| fishfile  | `TextFileHashMapRodDAO`, `TextFileHashMapBaitDAO` 등       | 텍스트 기반 DAO 구현체     |

---

##  개발 환경

- Java 17 이상  
- 콘솔 기반 애플리케이션  
- 객체 직렬화 기반 `.obj` 파일 저장 방식 사용
- Eclipse 사용

---

##  유스케이스 다이어그램

> 주요 사용자 유형(회원, 관리자)의 행위를 모델링한 유스케이스 다이어그램입니다.

![유스케이스 다이어그램](img/usecase.PNG)

---

##  전체 메뉴 흐름도

> 사용자와 관리자 메뉴의 흐름을 시각화한 다이어그램입니다.

![메뉴 흐름도](img/menu.png)

---

---

##  요구사항 정의
![요구사항-정의](./Requirements/Requirements%20definition.PNG)

### 요구사항명세-기능

#### 1. 낚시대(Rod)
![Rod 기능 요구사항](./Requirements/R-f(Rod).PNG)

#### 2. 미끼(Bait)
![Bait 기능 요구사항](./Requirements/R-f(Bait).PNG)

---

### 요구사항명세-비기능(데이터)

![비기능 요구사항 - 데이터](./Requirements/RN-f(데이터).PNG)

---

---

##  클래스 다이어그램

> 각 주요 도메인 객체의 구조를 나타낸 클래스 다이어그램입니다.

### 🎣 Rod 패키지  
![Rod 패키지](img/Rod.PNG)

### 🐛 Bait 패키지  
![Bait 패키지](img/Bait.PNG)

### 🛒 Cart 패키지  
![Cart 패키지](img/Cart.PNG)

### 👤 Member 패키지  
![Member 패키지](img/Member.PNG)

### 📦 Order 패키지  
![Order 패키지](img/Order.PNG)

---

<h3> 1. 관리자 기능 시연</h3>
<a href="https://youtu.be/hxXFCCQd9e4" target="_blank">
  <img src="https://img.youtube.com/vi/hxXFCCQd9e4/0.jpg" alt="관리자 기능 시연 영상 썸네일" width="480">
</a>
<p>관리자 모드에서 상품 등록, 수정, 삭제 기능을 시연한 영상입니다.</p>

<hr>

<h3> 2. 사용자 기능 시연</h3>
<a href="https://youtu.be/kzxOn6KzAsE" target="_blank">
  <img src="https://img.youtube.com/vi/kzxOn6KzAsE/0.jpg" alt="사용자 기능 시연 영상 썸네일" width="480">
</a>
<p>회원가입, 상품 탐색, 장바구니 담기, 주문/배송 확인을 시연한 영상입니다.</p>

---

##  코드 리뷰 

![CodeReview](https://github.com/user-attachments/assets/1f7d2ce6-4fdc-461a-92c0-8197c8cc05e0)

---
## 프로젝트 회고

### 어려웠던 문제점
- 클래스 구조 변경 후 `serialVersionUID` 불일치로 인한 `.obj` 파일 역직렬화 오류 발생  
  → `java.io.InvalidClassException` 예외로 인해 저장된 파일 로딩 실패
- 관리자/사용자 기능을 하나의 콘솔 앱 안에서 유기적으로 분리하는 구조 설계에 어려움

### 문제 해결 방법
- `serialVersionUID`를 명시적으로 선언하여 클래스 변경 시에도 파일 호환 가능하게 처리
- `.obj` 파일이 깨졌을 경우 자동 삭제 및 초기화 방식 적용
- 입력 유틸리티 클래스 `MyAppReader`를 분리하여 일관된 사용자 입력 처리 구현
- `FishingMartConsoleApp`에서 사용자/관리자 메뉴를 명확히 구분하고 흐름을 설계

###  배운 점
- 객체지향 설계의 중요성을 직접 체감하며, 클래스 간 역할 분리가 코드의 가독성과 유지보수성을 높여준다는 것을 배움
- 파일 입출력과 예외 처리 경험을 통해 실전 개발에서의 복잡한 상황을 대비할 수 있는 기초 역량을 다짐
- 개인 프로젝트로 비록 콘솔구현이지만 끝까지 구현해본 경험이 큰도전이었고 성장할 수 있었음




