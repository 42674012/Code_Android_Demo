package common.exception;

public class AuthTokenExpiredException extends RuntimeException {

    public AuthTokenExpiredException(String detailMessage) {

        super(detailMessage);
    }
}
