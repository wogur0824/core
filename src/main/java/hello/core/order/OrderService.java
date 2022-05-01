package hello.core.order;

public interface OrderService { // 클라이언트가 주문생성해서 주문 결과 반환하는 최종 order

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
