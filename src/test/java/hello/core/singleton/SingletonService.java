package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

// 싱글톤
public class SingletonService {

    private static SingletonService instance = new SingletonService();
    // JVM 내부에서 SingletonService()의 객체인 자기자신을 생성해서 instance 참조를 넣는다.

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
