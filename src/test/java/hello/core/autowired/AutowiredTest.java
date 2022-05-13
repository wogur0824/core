package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        // @Autowired(required = false)를 설정해두면
        // noBean1은 자동 주입 할 대상이 없으면 메서드 자체가 호출이 안된다.

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        // @Nullable을 넣어주면 noBean2은 호출은 되지만 null로 호출한다.

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
        // 자바 8에서 제공하는 Optional<T>문법이고,
        // noBean3이 스프링 빈이 없으면 Optional.empty으로 호출한다.
        
    }
}
