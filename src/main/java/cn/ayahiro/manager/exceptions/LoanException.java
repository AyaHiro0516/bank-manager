package cn.ayahiro.manager.exceptions;

public class LoanException extends ATMException {
    private static final long serialVersionUID = -2992843695318736912L;

    public LoanException() {
        super();
    }

    public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }
}
