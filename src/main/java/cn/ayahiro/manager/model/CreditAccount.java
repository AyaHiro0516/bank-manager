package cn.ayahiro.manager.model;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.exceptions.BalanceNotEnoughException;

public class CreditAccount extends Account {
    public CreditAccount() {
        super();
        this.setCeiling(0);
    }

    public CreditAccount(String password, String name, String personId, String email, String adress, double balance) {
        super(password, name, personId, email, adress, balance);
        this.setCeiling(0);
    }

    private double ceiling;

    public double getCeiling() {
        return ceiling;
    }

    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }

    @Override
    public CreditAccount withdraw(double amount) throws ATMException {
        CreditAccount updateAC = this;
        double delta = this.getBalance() - amount;
        if (delta < 0) {
            if (delta >= -this.getCeiling()) {
                this.setBalance(delta);
            } else {
                throw new BalanceNotEnoughException("Insufficient balance of overdraft, withdrawal failed.");
                //this.setBalance(-this.getCeiling());   //取钱透支不足  封顶
            }
        } else {
            this.setBalance(delta);
        }
        return updateAC;
    }

    @Override
    public String toString() {
        return "CreditAccount{ " + super.toString() +
                ", ceiling=" + ceiling +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreditAccount account = (CreditAccount) o;

        return Double.compare(account.ceiling, ceiling) == 0;
    }
}
