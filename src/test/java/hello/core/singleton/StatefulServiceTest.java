package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatefulService.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자A 10000원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: 사용자B 20000원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        // StatefulService 객체를 공용으로 사용하기 때문에
        // statefulService1이 10000원을 넣어서 사용자A의 주문 금액 조회를 하면 10000원을 기대했지만
        // 그 사이에 statefulService2가 20000원이 저장되므로 20000원이 출력된다.
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}