package lesson10;

public class LessThanZeroException extends Error {
    private int number;
    public LessThanZeroException(int number, String message) {
        super(message);
    }
}
