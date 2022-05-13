package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    // MyExcludeComponent가 붙은거는 제외하는 것
}
