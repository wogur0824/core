package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // AppConfig 파일은 애플리케이의 실제 동작에 필요한 구현 객체를 생성한다.
    // 즉, 클라이언트 파일이 추상인 interface 파일에만 의존하게 도와주는 것

    // 객체가 생성자를 통해 들어간다고 해서 -> 생서자 주입
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        // 고정 정액 할인 정책 -> 정률 할인 정책으로 바꾸는 것
    }

    // AppConfig의 리팩토링
    // new MemoryMemberRepository()의 부분을 중복되어 있어서 제거를 했다.
    // 그럼 MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
}
