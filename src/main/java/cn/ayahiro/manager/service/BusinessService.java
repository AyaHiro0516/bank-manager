package cn.ayahiro.manager.service;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.exceptions.BalanceNotEnoughException;
import cn.ayahiro.manager.exceptions.LoanException;
import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.LoanCreditAccount;
import cn.ayahiro.manager.model.LoanSavingAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("businessService")
public class BusinessService {
    @Resource(name = "accountMapper")
    private AccountMapper accountMapper;

    public void deposit(Account user, double amount) {
        user.deposit(amount);
        accountMapper.upDateBalance(user.getAccountType(), user.getUserName(), user.getBalance());
    }

    public void withdraw(Account user, double amount) throws ATMException {
        user.withdraw(amount);
        accountMapper.upDateBalance(user.getAccountType(), user.getUserName(), user.getBalance());
    }

    public void requestLoan(Account user, double amount) throws ATMException {
        String username = user.getUserName();
        if (user instanceof LoanCreditAccount) {
            ((LoanCreditAccount) user).requestLoan(amount);
            accountMapper.upDateLoan(user.getAccountType(), username, ((LoanCreditAccount) user).getLoan());
        } else if (user instanceof LoanSavingAccount) {
            ((LoanSavingAccount) user).requestLoan(amount);
            accountMapper.upDateLoan(user.getAccountType(), username, ((LoanCreditAccount) user).getLoan());
        } else {
            throw new LoanException("无借贷功能。");
        }
        accountMapper.upDateBalance(user.getAccountType(), username, user.getBalance());
    }

    public void payLoan(Account user, double amount) throws ATMException {
        String username = user.getUserName();
        if (user instanceof LoanCreditAccount) {
            ((LoanCreditAccount) user).payLoan(amount);
            accountMapper.upDateLoan(user.getAccountType(), username, ((LoanCreditAccount) user).getLoan());
        } else if (user instanceof LoanSavingAccount) {
            ((LoanSavingAccount) user).payLoan(amount);
            accountMapper.upDateLoan(user.getAccountType(), username, ((LoanCreditAccount) user).getLoan());
        } else {
            throw new LoanException("无借贷功能。");
        }
        accountMapper.upDateBalance(user.getAccountType(), username, user.getBalance());
    }

    public void transfer(Account fromUser, Account toUser, double amount) throws ATMException {
        if (fromUser.getBalance() >= amount) {
            fromUser.setBalance(fromUser.getBalance() - amount);
            toUser.setBalance(toUser.getBalance() + amount);
            accountMapper.upDateBalance(fromUser.getAccountType(), fromUser.getUserName(), fromUser.getBalance());
            accountMapper.upDateBalance(toUser.getAccountType(), toUser.getUserName(), toUser.getBalance());
        } else {
            throw new BalanceNotEnoughException("转账者余额不足。");
        }
    }
}
