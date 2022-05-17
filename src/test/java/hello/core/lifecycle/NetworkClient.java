package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// InitializingBean을 implements 하면 초기화 빈이 된다.
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스를 시작할 때 호출하는 메서드
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + ", message = " + message);
    }

    // 서비스를 종료할 때 호출하는 메서드
    public void disconnect() {
        System.out.println("close: " + url);
    }

//    @Override
    @PostConstruct
    public void init() {
        // 의존관계가 끝나면 호출해주겠다는 메소드
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

//    @Override
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
