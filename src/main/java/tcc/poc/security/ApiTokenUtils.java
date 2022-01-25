package tcc.poc.security;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

public class ApiTokenUtils {
    public static final String TOKEN = "token";

    public ApiTokenUtils() {
    }

    public static List<String> getScopes(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(TOKEN).as(UserPrincipal.class).getCredentials();
    }

    public static String getClientId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(TOKEN).as(UserPrincipal.class).getClientId();
    }

    public static Date getExpirationDate(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(TOKEN).as(UserPrincipal.class).getExpiration();
    }
}