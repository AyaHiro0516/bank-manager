package cn.ayahiro.manager.model;

import cn.ayahiro.manager.exceptions.ATMException;

public interface Loanable {
    void requestLoan(double money);
    void payLoan(double money) throws ATMException;
    double getLoan();
}
