package cn.ayahiro.manager.service;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.mapper.CreditAccountMapper;
import cn.ayahiro.manager.mapper.LoanCreditAccountMapper;
import cn.ayahiro.manager.mapper.LoanSavingAccountMapper;
import cn.ayahiro.manager.mapper.SavingAccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("businessService")
public class BusinessService {
    @Resource(name = "creditAccountMapper")
    private CreditAccountMapper creditAccountMapper;
    @Resource(name = "loanCreditAccountMapper")
    private LoanCreditAccountMapper loanCreditAccountMapper;
    @Resource(name = "savingAccountMapper")
    private SavingAccountMapper savingAccountMapper;
    @Resource(name = "loanSavingAccountMapper")
    private LoanSavingAccountMapper loanSavingAccountMapper;

    public void deposit(String username, double amount) {

    }

    public void withdraw(String username, double amount) {

    }

    public void requestLoan(String username, double amount) {

    }

    public void payLoan(String username, double amount) {

    }

    public void transfer(String fromname, String toname, double amount) throws ATMException{

    }
}
