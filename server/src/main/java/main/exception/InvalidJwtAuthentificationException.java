package main.exception;

public class InvalidJwtAuthentificationException extends RuntimeException {
    public InvalidJwtAuthentificationException(String message) {
        super(message);
    }
}
