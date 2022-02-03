package tcc.poc.models.enums;

public enum ValidationMessage {

    REQUEST_ERROR("Request error"),
    INVALID_WAREHOUSE("Invalid warehouse"),
    INVALID_MERCHANDISE("Invalid merchandise");

    private String message;

    ValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
