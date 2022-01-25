package tcc.poc.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class DefaultSecurityChecker {
    public DefaultSecurityChecker() {
    }

    public boolean allow(DecodedJWT decodedJWT, ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();
        SecuredApi securedApi = method.getDeclaredAnnotation(SecuredApi.class);
        List<String> requiredScopes = Arrays.asList(securedApi.allowedScopes());
        List<String> tokenScopes = ApiTokenUtils.getScopes(decodedJWT);
        Date exp = ApiTokenUtils.getExpirationDate(decodedJWT);
        tokenScopes.getClass();
        return hasRequiredScopes(requiredScopes, tokenScopes) && !isTokenExpired(exp);
    }

    private boolean isTokenExpired(Date exp) {
        return new Date().compareTo(exp) > 0;
    }

    private boolean hasRequiredScopes(List<String> requiredScopes, List<String> tokenScopes) {
        return requiredScopes.stream().anyMatch(tokenScopes::contains);
    }
}