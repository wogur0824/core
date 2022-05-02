package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    // Junit을 이용한 test
//  MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    @BeforeEach // 각 테스트가 실행되기전엔 이 어노테이션이 있는 것부터 실행을 한다.
    public  void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join() {

        // given
        Member member = new Member(1L, "A", Gradle.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
