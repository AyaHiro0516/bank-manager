package cn.ayahiro.manager.exceptions;

public class AccountNotFoundException extends ATMException {
    private static final long serialVersionUID = -2836547891952646405L;

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
