package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.BusinessBean;
import cn.ayahiro.manager.model.formbean.RegisterBean;
import cn.ayahiro.manager.utils.WebUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Temporal;
import java.util.HashMap;

@Service("registerService")
public class RegisterService {
    @Resource(name = "accountMapper")
    private AccountMapper accountMapper;

    private String[] acType={"CreditAccount", "LoanCreditAccount", "SavingAccount", "LoanSavingAccount"};

    public boolean checkBusinessBean(BusinessBean businessBean){
        boolean flag=true;
        String mode=businessBean.getMode();
        String fromName=businessBean.getFromName();
        String toName=businessBean.getToName();
        HashMap<String, String> error=businessBean.getError();
        if (!businessBean.validate()){
            if (("Transfer").equals(mode)){
                if (toName == null || toName.trim().equals("")) {
                    error.put("toName", "empty value");
                } else if (toName.equals(fromName)){
                    error.put("toName", "can not transfer to yourself!");
                }else {
                    if (!isRegister(toName)){
                        error.put("toName", "user does not exit!");
                    }
                }
            }
            flag=false;
        }else {
            if (("Transfer").equals(mode)){
                if (toName == null || toName.trim().equals("")) {
                    error.put("toName", "empty value");
                    flag=false;
                } else if (toName.equals(fromName)){
                    error.put("toName", "can not transfer to yourself!");
                    flag=false;
                }else {
                    if (!isRegister(toName)){
                        error.put("toName", "user does not exit!");
                        flag=false;
                    }
                }
            }
        }
        return flag;
    }

    public boolean checkRegisterBean(RegisterBean registerBean){
        boolean flag=true;
        if(!registerBean.validate()){
            if (isRegister(registerBean.getUserName())){
                registerBean.getError().put("isRegister", "username has been registered!");
            }
            flag=false;
        }else {
            if (isRegister(registerBean.getUserName())){
                registerBean.getError().put("isRegister", "username has been registered!");
                flag=false;
            }
        }
        return flag;
    }

    public void register(RegisterBean registerBean) {
        String accountType=registerBean.getAccountType();
        long userId= WebUtils.makeId();
        String passWord=registerBean.getPassWord();
        String personId=registerBean.getPersonId();
        String userName=registerBean.getUserName();
        String email=registerBean.getEmail();
        String address=registerBean.getAddress();
        accountMapper.register(accountType, userId, passWord, personId, userName, email, address, 0);
    }

    public boolean isRegister(String username){
        boolean flag=false;
        for (String type:acType){
            long count=accountMapper.isRegister(type, username);
            if (count==1){
                flag=true;
                break;
            }
        }
        return flag;
    }
}
