package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.formbean.BusinessBean;
import cn.ayahiro.manager.model.formbean.RegisterBean;
import cn.ayahiro.manager.utils.RegexUtil;
import cn.ayahiro.manager.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service("registerService")
public class RegisterService {
    @Resource(name = "accountMapper")
    private AccountMapper accountMapper;
    @Resource(name = "allowCheckBeanMapper")
    private AllowCheckBeanMapper allowCheckBeanMapper;

    private String[] acType = {"CreditAccount", "LoanCreditAccount", "SavingAccount", "LoanSavingAccount"};

    public boolean checkBusinessBean(BusinessBean businessBean) {
        boolean flag = true;
        String amount = businessBean.getAmount();
        String mode = businessBean.getMode();
        String fromName = businessBean.getFromName();
        String toName = businessBean.getToName();
        HashMap<String, String> error = businessBean.getError();

        if (amount == null || amount.trim().equals("")) {
            error.put("amount", "empty value!");
            flag = false;

        } else {
            if (!RegexUtil.amountValidation(amount)) {
                error.put("amount", "value must be +number!");
                flag = false;
            }
        }
        if (("Transfer").equals(mode)) {
            if (toName == null || toName.trim().equals("")) {
                error.put("toName", "empty value");
                flag = false;
            } else if (toName.equals(fromName)) {
                error.put("toName", "don't transfer to yourself!");
                flag = false;
            } else {
                if (!isRegister(toName)) {
                    error.put("toName", "user does not exit!");
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean checkRegisterBean(RegisterBean registerBean) {
        String userName = registerBean.getUserName();
        String passWord = registerBean.getPassWord();
        String passWord2 = registerBean.getPassWord2();
        String personId = registerBean.getPersonId();
        String email = registerBean.getEmail();
        HashMap<String, String> error = registerBean.getError();
        boolean flag = true;
        if (userName == null || userName.trim().equals("")) {
            error.put("userName", "empty value!");
            flag = false;
        } else {
            if (!RegexUtil.userNameValidation(userName)) {
                error.put("userName", "wrong input!");
                flag = false;
            }
        }

        if (isRegister(registerBean.getUserName())) {
            registerBean.getError().put("isRegister", "sorry, this name has been registered!");
            flag = false;
        }

        if (passWord == null || passWord.trim().equals("")) {
            error.put("passWord", "empty value!");
            flag = false;
        } else {
            if (!RegexUtil.passWordValidation(passWord)) {
                error.put("passWord", "wrong input!");
                flag = false;
            }
        }

        if (passWord2 == null || passWord2.trim().equals("")) {
            error.put("passWord2", "empty value!");
            flag = false;
        } else {
            if (passWord == null || passWord.trim().equals("")) {
                error.put("passWord2", "you haven't input passWord!");
                flag = false;
            } else if (!passWord2.equals(passWord)) {
                error.put("passWord2", "inconsistent password!");
                flag = false;
            }
        }

        if (personId == null || personId.trim().equals("")) {
            error.put("personId", "empty value!");
            flag = false;
        } else {
            if (!RegexUtil.personIdValidation(personId)) {
                error.put("personId", "wrong input!");
                flag = false;
            }
        }

        if (email == null || email.trim().equals("")) {
            error.put("email", "empty value!");
            flag = false;
        } else {
            if (!RegexUtil.emailValidation(email)) {
                error.put("email", "the mailbox format is illegal!");
                flag = false;
            }
        }
        return flag;
    }

    public void register(RegisterBean registerBean) {
        String accountType = registerBean.getAccountType();
        long userId = UserUtil.makeId();
        String passWord = registerBean.getPassWord();
        String personId = registerBean.getPersonId();
        String userName = registerBean.getUserName();
        String email = registerBean.getEmail();
        String address = registerBean.getAddress();
        accountMapper.register(accountType, userId, UserUtil.getMD5(passWord), personId, userName, email, address, 0);
    }

    public boolean isRegister(String username) {
        boolean flag = false;
        for (String type : acType) {
            long count = accountMapper.isRegister(type, username);
            if (count == 1) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void registerBean(String userName, int missNum, boolean isAllow, String role, String permission) {
        allowCheckBeanMapper.registerBean(userName, missNum, isAllow, role, permission);
    }
}
