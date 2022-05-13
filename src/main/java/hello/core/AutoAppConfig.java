package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // excludeFilters = @ComponentScan.Filter는 componentscan해서 다 찾아서 스프링 빈으로 자동 등록을 하는데
        // 그 중에 뺼것을 지정해주는 것
        // Configuration 어노테이션이 붙은 class는 뺀다
        // 왜? Configuration 어노테이션이 파일은 AppConfig 인데 이 파일은 수동으로 등록해주는 거라
        // AppConfig을 등록하면 중복으로 충돌이 일어나므로 AppConfig 파일은 componentscan에서 뺴준다.
)  // 스프링 빈을 자동으로 끌어올리는 어노테이션
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
