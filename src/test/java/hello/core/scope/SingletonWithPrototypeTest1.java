package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getI()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getI()).isEqualTo(1);
    }

    @Test
    void SingletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean; // 생성시점에 주입이 되어있다. 그래서 계속 같은 것을 쓴다.

        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;
//        === private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        // 원래 ObjectFactory가 있었는데 ObjectProvider가 ObjectFactory를 상속하여 더 많은 기능을 제공해준다.

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        public int logic() {
//            prototypeBean.addCount();
//
//            int i = prototypeBean.getI();
//            return i;
//            // 같은 의미 == return prototypeBean.getI();
//        }

        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            // getObject를 호출하면 그때서야 스프링 컨테이너에서 prototypeBean를 찾아서 반환해주는 기능
            // prototypeBeanProvider.getObject() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
            prototypeBean.addCount();

            int i = prototypeBean.getI();
            return i;
            // 같은 의미 == return prototypeBean.getI();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int i = 0;

        public void addCount(){
            i++;
        }

        public int getI() {
            return i;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this); // this로 현재 나를 찍어준다.
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
