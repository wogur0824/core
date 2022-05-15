package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Gradle;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// 순수한 자바코드로 테스트 코드 만들기
class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "JAEHYEOK", Gradle.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        // DI 주입을 생성자로 지정을 하면 필수값이 필요하기 때문에 임의로 MemoryMemberRepository와 FixDiscountPolicy로 넣어준다.

        Order order = orderService.createOrder(1L, "itemABC", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // NullPointerException 오류가 뜬다.
    // OrderServiceImpl에 있는 createOrder 메소드에서 memberrepository와 discountpolicy을 사용하기 때문에
    // memberrepository와 discountpolicy의 갑을 세팅해주어야 한다.

}