package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.*;
import cn.ayahiro.manager.model.formbean.AllowCheckBean;
import cn.ayahiro.manager.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource(name = "allowCheckBeanMapper")
    private AllowCheckBeanMapper allowCheckBeanMapper;

    private String[] acType = {"CreditAccount", "LoanCreditAccount", "SavingAccount", "LoanSavingAccount"};

    public Account getUserByNameAndPassWord(String username, String password) {
        CreditAccount creditAccount = null;
        LoanCreditAccount loanCreditAccount = null;
        SavingAccount savingAccount = null;
        LoanSavingAccount loanSavingAccount = null;
        String md5_str = UserUtil.getMD5(password);
        for (String type : acType) {
            switch (type) {
                case "CreditAccount":
                    creditAccount = creditAccountMapper.findUser(username, md5_str);
                    if (creditAccount != null) {
                        return creditAccount;
                    }
                    break;
                case "LoanCreditAccount":
                    loanCreditAccount = loanCreditAccountMapper.findUser(username, md5_str);
                    if (loanCreditAccount != null) {
                        return loanCreditAccount;
                    }
                    break;
                case "SavingAccount":
                    savingAccount = savingAccountMapper.findUser(username, md5_str);
                    if (savingAccount != null) {
                        return savingAccount;
                    }
                    break;
                case "LoanSavingAccount":
                    loanSavingAccount = loanSavingAccountMapper.findUser(username, md5_str);
                    if (loanSavingAccount != null) {
                        return loanSavingAccount;
                    }
                    break;
            }
        }
        return null;
    }

    public Account getUserByUserName(String username) {
        CreditAccount creditAccount = null;
        LoanCreditAccount loanCreditAccount = null;
        SavingAccount savingAccount = null;
        LoanSavingAccount loanSavingAccount = null;
        for (String type : acType) {
            switch (type) {
                case "CreditAccount":
                    creditAccount = creditAccountMapper.getUser(username);
                    if (creditAccount != null) {
                        return creditAccount;
                    }
                    break;
                case "LoanCreditAccount":
                    loanCreditAccount = loanCreditAccountMapper.getUser(username);
                    if (loanCreditAccount != null) {
                        return loanCreditAccount;
                    }
                    break;
                case "SavingAccount":
                    savingAccount = savingAccountMapper.getUser(username);
                    if (savingAccount != null) {
                        return savingAccount;
                    }
                    break;
                case "LoanSavingAccount":
                    loanSavingAccount = loanSavingAccountMapper.getUser(username);
                    if (loanSavingAccount != null) {
                        return loanSavingAccount;
                    }
                    break;
            }
        }
        return null;
    }

    public List<Account> getAllUsers() {
        List<Account> accountList = new ArrayList<>();
        if (savingAccountMapper.getAllUsers().size() != 0) {
            accountList.addAll(savingAccountMapper.getAllUsers());
        }
        if (creditAccountMapper.getAllUsers().size() != 0) {
            accountList.addAll(creditAccountMapper.getAllUsers());
        }
        if (loanSavingAccountMapper.getAllUsers().size() != 0) {
            accountList.addAll(loanSavingAccountMapper.getAllUsers());
        }
        if (loanCreditAccountMapper.getAllUsers().size() != 0) {
            accountList.addAll(loanCreditAccountMapper.getAllUsers());
        }
        return accountList;
    }

    public List<Account> getUsersByPage(int pageNum, int pageSize) {
        List<Account> accountList = getAllUsers();
        return accountList.stream()
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    public AllowCheckBean getBeanByUserName(String userName) {
        return allowCheckBeanMapper.getBeanByUserName(userName);
    }

    public void upDateMissNum(int missNum, String userName) {
        allowCheckBeanMapper.upDateMissNum(missNum, userName);
    }

    public void upDateIsAllow(boolean isAllow, String userName) {
        allowCheckBeanMapper.upDateIsAllow(isAllow, userName);
    }
}
