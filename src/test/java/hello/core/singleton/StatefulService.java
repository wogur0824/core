package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 10000원(userA) -> 20000원(userB)으로 바뀌게 된다

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!!!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
