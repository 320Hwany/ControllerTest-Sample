package controllertest.error;

public record ErrorResponse(
        String statusCode,
        String message
) {
    public static ErrorResponse of(final String statusCode, final String message) {
        return new ErrorResponse(statusCode, message);
    }
}
