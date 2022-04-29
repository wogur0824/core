package hello.core.member;

// enum class란?
// 우리가 흔히 상수를 정의할 때 final static string과 같은 방식으로 상수를 정의한다.
// 하지만 이렇게 상수를 정의해서 코딩하는 경우 문제가 발생되는데
// 이러한 문제를 보와하기 위해 java에서 추가된것이 enum이다.
// enum은 열거형이라고 불리며, 서로 연관된 상수들의 집합을 의미한다.
// 기존에 final static string과 같이 문자열이나 숫자들을 나타내는 기본자료형의 값을
// enum을 이용해 같은 효과를 낼 수 있다.


public enum Gradle {

    // 회원등급
    BASIC,
    VIP
}
