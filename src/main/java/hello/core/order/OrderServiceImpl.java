package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
// lombok 라이브러리의 기능 중 하나로 이 어노테이션만 붙이면 final로 붙은 필드를 자동으로 생성자를 만들어 준다.
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


    // 기존에 있던 private final MemberRepository memberRepository와 private final DiscountPolicy discountPolicy의
    // final를 삭제해주고 setter라 불리는 수정자 메서드를 통해 의존관계를 주입시켜주는 방법이다.
    // 수정자 주입(setter 주입)
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("2.memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("2.discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired
    // 다양한 의존관계 주입 방법 중에 하나인 생성자 주입
    // 생성자가 딱 1개만 있을 경우, @Autowired 어노테이션을 생략할 수 있다.
    // 2개 이상부터는 어떤 생성자를 주입시킬지 @Autowired로 지정을 해주어야 한다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // @Autowired는 타입을 보고 매칭을 하는데 이때, 타입안에 2개 이상의 빈이 존재할 때
        // 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
        // 그래서 discountpolicy안에 있는 빈이 fix와 rate 두개의 빈이 존재하므로
        // discountpolicy의 파라미터(매개변수)로 rate를 지정해주면 오류가 해결이 된다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 다양한 의존관계 주입 방법 중에 하나인 일반 메서드 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 다양한 의존관계 주입 방법 중에 하나인 필드 주입
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private  DiscountPolicy discountPolicy;

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
