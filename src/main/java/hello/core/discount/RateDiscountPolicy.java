package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary // 조회된 타입안에 빈이 2개이상 있을 경우 이 어노테이션이 붙어있는 빈을 우선권을 지정한다.
//@Qualifier("mainDiscountPolicy")
// maindiscountpolicy의 이름을 붙여 orderserviceimpl에 있는 생성자 파라미터에 추가로 붙여주면 된다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{
    // 기존에 있던 고정 정액 할인을 하지 않고 산 금액의 10%를 할인해주는 정책으로 바꾸는 것

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGradle() == Gradle.VIP) {
            return price * discountPercent / 100;
        }else {
            return 0;
        }
    }
}
