package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Bean 어노테이션을 설정해주면 @Bean 어노테이션이 붙은 메소드들이 spring컨테이너에 등록이 된다.
// DI 컨테이너(어샘블러 or 오브젝트 팩토리) => AppConfig

// @Configuration을 주석처리하고 test 코드를 돌리면 순수자를 읽으면서 한 객체를 사용하는게 아니고
// 호출할때마다 새로운 객체를 넣음으로써 싱글톤 패턴이 깨지게 된다.

@Configuration
public class AppConfig {
    // AppConfig 파일은 애플리케이의 실제 동작에 필요한 구현 객체를 생성한다.
    // 즉, 클라이언트 파일이 추상인 interface 파일에만 의존하게 도와주는 것

    // 객체가 생성자를 통해 들어간다고 해서 -> 생서자 주입

    // @Bean memberService -> new MemoryMemberRepository() 호출!!
    // @Bean orderService -> new MemoryMemberRepository() 호출!!
    // 이러면 2번 호출되니깐 싱글톤이 깨지는거 아닌가요?
    // Test코드에서 실험해보자!!

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        // 고정 정액 할인 정책 -> 정률 할인 정책으로 바꾸는 것
    }
    //
    // AppConfig의 리팩토링
    // new MemoryMemberRepository()의 부분을 중복되어 있어서 제거를 했다.
    // 그럼 MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
}
