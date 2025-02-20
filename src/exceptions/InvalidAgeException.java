package exceptions;

public class InvalidAgeException extends Error{
    public InvalidAgeException(String message) {
        super(message);
    }
}
