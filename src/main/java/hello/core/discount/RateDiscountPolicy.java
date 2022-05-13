package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
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
