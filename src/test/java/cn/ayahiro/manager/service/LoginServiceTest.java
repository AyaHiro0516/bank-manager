package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.*;
import cn.ayahiro.manager.model.formbean.AllowCheckBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
    public void getUserByNameAndPassWord() throws Exception {
    }

    @Test
    public void getUserByName() throws Exception {
    }

    @Test
    public void getBeanByName() throws Exception {
        AllowCheckBean allowCheckBean=allowCheckBeanMapper.getBeanByUserName("bxy0516");
        System.out.println(allowCheckBean.toString());
    }

}