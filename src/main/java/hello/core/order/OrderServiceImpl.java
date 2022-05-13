package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // memberrepository에서 회원을 찾아야 됨
    private final MemberRepository memberRepository;
    // 회원 등급이 뭔지를 알아야되서 discountpolicy에서 찾아야 됨
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private  final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 기존에 있던 고정 정액 할인에서 새로운 할인정책인 정률 할인으로 바꾸는 법
    // (추상에만 의존하는게 아니고 구현체에도 의존하므로 DIP, OCP 위반)

    private final DiscountPolicy discountPolicy;
    // 1. orderservice는 interface 즉, 추상에만 의존해야되서 private DiscountPolicy discountPolicy;만 적어준다. (DIP 지킴)
    // 2. final을 붙이지 않은 이유는 final이 붙으면 무조건 값이 할당되어야 하니깐 빼준다.
    // 3. 하지만 이렇게만 설정해놓으면 discountPolicy가 아무 값도 할당이 안되어서 null이여서 오류가 터진다.


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
