package by.epam.xmlparsing.exception;

public class ParseTourException extends Exception {

    public ParseTourException() {
        super();
    }

    public ParseTourException(String message) {
        super(message);
    }

    public ParseTourException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseTourException(Throwable cause) {
        super(cause);
    }
}
