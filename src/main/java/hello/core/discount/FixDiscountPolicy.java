package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;

import java.security.PrivateKey;

public class FixDiscountPolicy implements DiscountPolicy{ // 고정 정액 할인 정책

    private int discountFixAmount = 1000; // VIP등급인 회원은 무조건 1000원 할인 적용

    @Override
    public int discount(Member member, int price) {
        if (member.getGradle() == Gradle.VIP) { // 회원 등급이 VIP인지 확인하는 if문
            return discountFixAmount;
        } else { // VIP가 아닌 그 외 일반 회원
            return 0;
        }
    }
}
