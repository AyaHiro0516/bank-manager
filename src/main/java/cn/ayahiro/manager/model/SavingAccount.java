package cn.ayahiro.manager.model;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.exceptions.BalanceNotEnoughException;

public class SavingAccount extends Account {
    public SavingAccount() {
        super();
    }

    public SavingAccount(String password, String name, String personId, String email, String adress, double balance) {
        super(password, name, personId, email, adress, balance);
    }

    @Override
    public Account withdraw(double amount) throws ATMException {
        Account updateAC = this;
        if (this.getBalance() - amount < 0) throw new BalanceNotEnoughException("余额不足，取款失败。");
            //updateAC.setBalance(0);  //取钱余额不足  直接设为0
        else
            updateAC.setBalance(this.getBalance() - amount);
        return updateAC;
    }

    @Override
    public String toString() {
        return "SavingAccount{ " + super.toString() + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return super.equals(o);
    }
}
