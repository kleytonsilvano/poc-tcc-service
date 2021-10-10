package tcc.poc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import tcc.poc.models.enums.ValidationMessage;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationException extends HttpClientErrorException {

    private static final long serialVersionUID = -2906713668353779395L;

    public ValidationException(ValidationMessage validationMessage) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, validationMessage.getMessage());
    }

}