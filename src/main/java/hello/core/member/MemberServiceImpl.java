package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 가입을 하고 회원을 찾을려면 memberrepository가 필요하므로 적어주고 구현객체인 memorymemberrepository를 넣어준다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private MemberRepository memberRepository;
    // AppConfig 파일안에 설정을 함으로써 추상에만 의존한다.
    // 그러므로 DIP을 지켜진다.

    @Autowired // 자동 의존 관계 주입
    // 마치 @Autowired(ac.getBean(MemberRepository.class)) 처럼 동작을 하는 것처럼 자동으로 들어간다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        // join에서 save를 호출하면 다형성에 의해 memorymemberrepository에 있는 save가 호출이 된다.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}