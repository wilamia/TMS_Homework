package exceptions;

public class InvalidCustomException extends Error{
    private String command;

    public  InvalidCustomException(String command, String message) {
        super(message);
        this.command = command;
    }

}
