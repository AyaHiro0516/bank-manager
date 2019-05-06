package cn.ayahiro.manager.exceptions;

public class ATMException extends Exception{
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
