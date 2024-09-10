package srbn.Backend.Domain;

public class ErrorE extends Exception{

    public ErrorE(String message) {
        super(message);
    }

    public ErrorE(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorE(Throwable cause) {
        super(cause);
    }

    public ErrorE(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
