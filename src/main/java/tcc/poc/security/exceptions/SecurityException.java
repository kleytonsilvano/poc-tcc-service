package tcc.poc.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SecurityException extends HttpClientErrorException {

    public SecurityException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public SecurityException(int httpStatus, String message) {
        super(HttpStatus.valueOf(httpStatus), message);
    }
}