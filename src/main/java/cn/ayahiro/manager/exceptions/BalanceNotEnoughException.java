package cn.ayahiro.manager.exceptions;

public class BalanceNotEnoughException extends ATMException {
    private static final long serialVersionUID = -8683658287138212865L;

    public BalanceNotEnoughException() {
        super();
    }

    public BalanceNotEnoughException(String message) {
        super(message);
    }

    public BalanceNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
