package tcc.poc.security;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.METHOD})
public @interface SecuredApi {
    String[] allowedScopes() default {};
}
