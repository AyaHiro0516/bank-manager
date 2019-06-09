package cn.ayahiro.manager.exceptions;

public class ATMException extends Exception {
    private static final long serialVersionUID = -3797014097157301319L;

    public ATMException() {
        super();
    }

    public ATMException(String message) {
        super(message);
    }

    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }
}
