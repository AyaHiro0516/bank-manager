package cn.ayahiro.manager.model;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.exceptions.BalanceNotEnoughException;
import cn.ayahiro.manager.exceptions.LoanException;

public class LoanCreditAccount extends CreditAccount implements Loanable {
    private static final long serialVersionUID = 1008758534988891639L;
    private double loan;

    public void setLoan(double loan) throws ATMException {
        if (loan < 0) {
            throw new LoanException("The loan amount cannot be negative and the setting fails.");
        } else this.loan = loan;
    }

    public LoanCreditAccount() {
        super();
    }

    public LoanCreditAccount(String password, String name, String personId, String email, String adress, double balance) {
        super(password, name, personId, email, adress, balance);
    }

    @Override
    public void requestLoan(double money) {
        this.loan += money;
        this.setBalance(this.getBalance() + money);
    }

    @Override
    public void payLoan(double money) throws ATMException {
        if (this.getBalance() >= money) {
            if (this.loan >= money) {
                this.loan -= money;
                this.setBalance(this.getBalance() - money);
            } else throw new LoanException("The repayment amount is greater than the loan amount.");
        } else throw new BalanceNotEnoughException("Insufficient balance, repayment failed.");
    }

    @Override
    public double getLoan() {
        return this.loan;
    }

    @Override
    public String toString() {
        return "LoanCreditAccount{ " + super.toString() +
                ", loan=" + loan +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LoanCreditAccount that = (LoanCreditAccount) o;

        return Double.compare(that.loan, loan) == 0;
    }
}
