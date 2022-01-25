package tcc.poc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tcc.poc.security.exceptions.SecurityException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GenericSecurityAspect {
    private static final Logger logger = LoggerFactory.getLogger(GenericSecurityAspect.class);
    protected static final String API_KEY= "X-API-Key";
    protected static final String INSUFFICIENT_PRIVILEGES_MESSAGE = "Insufficient privileges.";
    protected static final String BAD_JWT_MESSAGE = "Bad JWT on X-API-Token header";
    private static Map<Class<?>, DefaultSecurityChecker> checkerInstances = new HashMap();

    public GenericSecurityAspect() {
    }

    protected void invokeSecurityChecker(ProceedingJoinPoint pjp, DecodedJWT decodedJWT, Class<? extends DefaultSecurityChecker> checkerClass) throws InstantiationException, IllegalAccessException, SecurityException {
        if (!this.getSecurityCheckerInstance(checkerClass).allow(decodedJWT, pjp)) {
            throw new SecurityException(INSUFFICIENT_PRIVILEGES_MESSAGE);
        }
    }

    protected DecodedJWT getDecodedJWT() throws SecurityException {
        HttpServletRequest request = this.getHttpServletRequest();
        String jwt = request.getHeader(API_KEY);
        if (jwt == null) {
            throw new SecurityException(INSUFFICIENT_PRIVILEGES_MESSAGE);
        } else {
            try {
                DecodedJWT decodedJWT = JWT.decode(jwt);
                request.setAttribute("DECODED_JWT_TOKEN", decodedJWT);
                return decodedJWT;
            } catch (JWTDecodeException var4) {
                throw new SecurityException(BAD_JWT_MESSAGE);
            }
        }
    }

    protected HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        return request;
    }

    private DefaultSecurityChecker getSecurityCheckerInstance(Class<? extends DefaultSecurityChecker> clazz) throws InstantiationException, IllegalAccessException {
        DefaultSecurityChecker interSecurityChecker = checkerInstances.get(clazz);
        if (interSecurityChecker == null) {
            interSecurityChecker = clazz.newInstance();
            checkerInstances.put(clazz, interSecurityChecker);
        }

        return interSecurityChecker;
    }
}
