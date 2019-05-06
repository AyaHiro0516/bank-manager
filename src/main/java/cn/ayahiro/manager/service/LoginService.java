package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("loginService")
public class LoginService {
    @Resource(name = "creditAccountMapper")
    private CreditAccountMapper creditAccountMapper;
    @Resource(name = "loanCreditAccountMapper")
    private LoanCreditAccountMapper loanCreditAccountMapper;
    @Resource(name = "savingAccountMapper")
    private SavingAccountMapper savingAccountMapper;
    @Resource(name = "loanSavingAccountMapper")
    private LoanSavingAccountMapper loanSavingAccountMapper;

    private String[] acType={"CreditAccount", "LoanCreditAccount", "SavingAccount", "LoanSavingAccount"};

    public Account findUser(String username, String password){
        CreditAccount creditAccount=null;
        LoanCreditAccount loanCreditAccount=null;
        SavingAccount savingAccount=null;
        LoanSavingAccount loanSavingAccount=null;
        for (String type:acType){
            switch (type){
                case "CreditAccount":
                    creditAccount=creditAccountMapper.findUser(username, password);
                    if (creditAccount!=null) {
                        creditAccount.setAccountType("CreditAccount");
                        return creditAccount;
                    }
                    break;
                case "LoanCreditAccount":
                    loanCreditAccount=loanCreditAccountMapper.findUser(username, password);
                    if (loanCreditAccount!=null) {
                        loanCreditAccount.setAccountType("LoanCreditAccount");
                        return loanCreditAccount;
                    }
                    break;
                case "SavingAccount":
                    savingAccount=savingAccountMapper.findUser(username, password);
                    if (savingAccount!=null) {
                        savingAccount.setAccountType("SavingAccount");
                        return savingAccount;
                    }
                    break;
                case "LoanSavingAccount":
                    loanSavingAccount=loanSavingAccountMapper.findUser(username, password);
                    if (loanSavingAccount!=null) {
                        loanSavingAccount.setAccountType("LoanSavingAccount");
                        return loanSavingAccount;
                    }
                    break;
            }
        }
        return null;
    }
}
