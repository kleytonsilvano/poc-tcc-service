package tcc.poc.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SecurityAspect extends GenericSecurityAspect {
    public SecurityAspect() { }


    @Around("@annotation(tcc.poc.security.SecuredApi)")
    public Object verifySecurity(ProceedingJoinPoint pjp) throws Throwable {
        this.invokeSecurityChecker(pjp, this.getDecodedJWT(), DefaultSecurityChecker.class);
        return pjp.proceed();
    }
}




