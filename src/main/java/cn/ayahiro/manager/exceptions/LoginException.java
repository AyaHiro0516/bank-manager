package cn.ayahiro.manager.exceptions;

public class LoginException extends ATMException {
    private static final long serialVersionUID = -254183503735624354L;

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
