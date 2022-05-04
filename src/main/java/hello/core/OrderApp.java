package hello.core;

import hello.core.member.Gradle;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//      MemberService memberService = new MemberServiceImpl();
//      OrderService orderService = new OrderServiceImpl();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "A", Gradle.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "pringles", 20000);

        System.out.println("order: " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice()); // 기존 아이템 가격에서 할인된 가격을 뺸 가격
    }
}
