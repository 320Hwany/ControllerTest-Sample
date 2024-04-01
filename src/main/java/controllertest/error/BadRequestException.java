package controllertest.error;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final String statusCode = "400";
    private final String message;

    public BadRequestException(final String message) {
        this.message = message;
    }
}
