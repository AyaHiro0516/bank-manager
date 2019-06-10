package cn.ayahiro.manager.service;

import cn.ayahiro.manager.aspect.LoginAspect;
import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.exceptions.AccountNotFoundException;
import cn.ayahiro.manager.exceptions.LoginException;
import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.*;
import cn.ayahiro.manager.model.formbean.AjaxResponseBody;
import cn.ayahiro.manager.model.formbean.AllowCheckBean;
import cn.ayahiro.manager.model.formbean.ConditionBean;
import cn.ayahiro.manager.model.formbean.LoginBean;
import cn.ayahiro.manager.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    private enum AccountType {
        SavingAccount, CreditAccount, LoanSavingAccount, LoanCreditAccount
    }

    public void checkUserIsAllow(LoginBean loginBean, AjaxResponseBody result) throws ATMException {
        String userName = loginBean.getUserName();
        String passWord = loginBean.getPassWord();
        Account user = getUserByNameAndPassWord(userName, passWord);
        AllowCheckBean checkBean = getBeanByUserName(userName);
        if (user == null) {
            //1若该用户存在  说明密码错误， update missNum  达到5次  isAllow置为false  missNum清零
            if (checkBean != null && checkBean.isAllow()) {
                if (checkBean.getMissNum() >= 5) {
                    upDateMissNum(0, userName);
                    upDateIsAllow(false, userName);
                    result.setMsg("Today's login opportunity has been used up.");
                    throw new LoginException("No login opportunity.");
                } else {
                    upDateMissNum(checkBean.getMissNum() + 1, userName);
                    int chance = 4 - checkBean.getMissNum();
                    if (chance == 0) {
                        result.setMsg("Today's login opportunity has been used up.");
                        throw new LoginException("No login opportunity.");
                    } else {
                        result.setMsg("Wrong password! " + chance + " chance(s) left.");
                    }
                }
            } else if (checkBean != null && !checkBean.isAllow()) {
                result.setMsg("Today's login opportunity has been used up.");
                throw new LoginException("No login opportunity.");
            } else {
                //2若该用户不存在  就是不存在  不作操作
                result.setMsg("User does not exist, or empty passWord!");
                throw new AccountNotFoundException("User does not exist, or empty passWord.");
            }
        } else {
            //此处判断miss_time和isAllow
            if (checkBean.isAllow() && checkBean.getMissNum() < 5) {
                //唯一登录成功的情况
                result.setMsg("success");
            } else if (!checkBean.isAllow()) {
                result.setMsg("Today's login opportunity has been used up.");
                throw new LoginException("No login opportunity.");
            }
        }
    }

    public Account getUserByNameAndPassWord(String username, String password) {
        CreditAccount creditAccount = null;
        LoanCreditAccount loanCreditAccount = null;
        SavingAccount savingAccount = null;
        LoanSavingAccount loanSavingAccount = null;
        String md5_str = UserUtil.getMD5(password);
        for (AccountType type : AccountType.values()) {
            switch (type) {
                case CreditAccount:
                    creditAccount = creditAccountMapper.findUser(username, md5_str);
                    if (creditAccount != null) {
                        return creditAccount;
                    }
                    break;
                case LoanCreditAccount:
                    loanCreditAccount = loanCreditAccountMapper.findUser(username, md5_str);
                    if (loanCreditAccount != null) {
                        return loanCreditAccount;
                    }
                    break;
                case SavingAccount:
                    savingAccount = savingAccountMapper.findUser(username, md5_str);
                    if (savingAccount != null) {
                        return savingAccount;
                    }
                    break;
                case LoanSavingAccount:
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
        for (AccountType type : AccountType.values()) {
            switch (type) {
                case CreditAccount:
                    creditAccount = creditAccountMapper.getUser(username);
                    if (creditAccount != null) {
                        return creditAccount;
                    }
                    break;
                case LoanCreditAccount:
                    loanCreditAccount = loanCreditAccountMapper.getUser(username);
                    if (loanCreditAccount != null) {
                        return loanCreditAccount;
                    }
                    break;
                case SavingAccount:
                    savingAccount = savingAccountMapper.getUser(username);
                    if (savingAccount != null) {
                        return savingAccount;
                    }
                    break;
                case LoanSavingAccount:
                    loanSavingAccount = loanSavingAccountMapper.getUser(username);
                    if (loanSavingAccount != null) {
                        return loanSavingAccount;
                    }
                    break;
            }
        }
        return null;
    }

    public List<Account> getAllUsersByType(ConditionBean conditionBean) {
        List<Account> accountList = new ArrayList<>();
        List<SavingAccount> savingAccounts = savingAccountMapper.getAllUsers();
        List<CreditAccount> creditAccounts = creditAccountMapper.getAllUsers();
        List<LoanSavingAccount> loanSavingAccounts = loanSavingAccountMapper.getAllUsers();
        List<LoanCreditAccount> loanCreditAccounts = loanCreditAccountMapper.getAllUsers();
        if (conditionBean.getIsChooseSA()) {
            if (savingAccounts.size() != 0) {
                accountList.addAll(savingAccounts);
            }
        }
        if (conditionBean.getIsChooseCA()) {
            if (creditAccounts.size() != 0) {
                accountList.addAll(creditAccounts);
            }
        }
        if (conditionBean.getIsChooseLSA()) {
            if (loanSavingAccounts.size() != 0) {
                accountList.addAll(loanSavingAccounts);
            }
        }
        if (conditionBean.getIsChooseLCA()) {
            if (loanCreditAccounts.size() != 0) {
                accountList.addAll(loanCreditAccounts);
            }
        }
        return accountList;
    }

    public List<Account> getUsersByPage(int pageNum, int pageSize, ConditionBean conditionBean) {
        List<Account> accountList = new ArrayList<>();
        List<SavingAccount> savingAccounts = savingAccountMapper.getAllUsers();
        List<CreditAccount> creditAccounts = creditAccountMapper.getAllUsers();
        List<LoanSavingAccount> loanSavingAccounts = loanSavingAccountMapper.getAllUsers();
        List<LoanCreditAccount> loanCreditAccounts = loanCreditAccountMapper.getAllUsers();
        if (conditionBean.getIsChooseSA()) {
            if (savingAccounts.size() != 0) {
                accountList.addAll(savingAccounts);
            }
        }
        if (conditionBean.getIsChooseCA()) {
            if (creditAccounts.size() != 0) {
                accountList.addAll(creditAccounts);
            }
        }
        if (conditionBean.getIsChooseLSA()) {
            if (loanSavingAccounts.size() != 0) {
                accountList.addAll(loanSavingAccounts);
            }
        }
        if (conditionBean.getIsChooseLCA()) {
            if (loanCreditAccounts.size() != 0) {
                accountList.addAll(loanCreditAccounts);
            }
        }
        return accountList.stream()
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public AllowCheckBean getBeanByUserName(String userName) {
        return allowCheckBeanMapper.getBeanByUserName(userName);
    }

    public void upDateMissNum(int missNum, String userName) {
        LOGGER.info("用户: {}输入密码错误次数为: {}", userName, missNum);
        allowCheckBeanMapper.upDateMissNum(missNum, userName);
    }

    public void upDateIsAllow(boolean isAllow, String userName) {
        if (!isAllow) {
            LOGGER.info("用户: {}的账号被锁定了", userName);
        } else {
            LOGGER.info("用户: {}的账号已解除锁定", userName);
        }
        allowCheckBeanMapper.upDateIsAllow(isAllow, userName);
    }
}
