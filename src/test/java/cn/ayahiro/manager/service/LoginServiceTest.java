package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.*;
import cn.ayahiro.manager.model.formbean.AllowCheckBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {
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

    @Test
    public void getAllUsers() {
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
        for (Account account : accountList) {
            System.out.println(account.toString());
        }
    }

    @Test
    public void getBeanByName() throws Exception {
        AllowCheckBean allowCheckBean = allowCheckBeanMapper.getBeanByUserName("bxy0516");
        System.out.println(allowCheckBean.toString());
    }

}