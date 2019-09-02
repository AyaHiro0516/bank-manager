package cn.ayahiro.manager.service;

import cn.ayahiro.manager.mapper.AccountMapper;
import cn.ayahiro.manager.mapper.AllowCheckBeanMapper;
import cn.ayahiro.manager.utils.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {
    @Resource(name = "accountMapper")
    private AccountMapper accountMapper;
    @Resource(name = "allowCheckBeanMapper")
    private AllowCheckBeanMapper allowCheckBeanMapper;

    private String[] acType = {"creditaccount", "loancreditaccount", "savingaccount", "loansavingaccount"};

    @Test
    public void register() throws Exception {
//        for (int i = 0; i < 56; ++i) {
//            int type = new Random().nextInt(4);
//            String accountType = acType[type];
//            long userId = UserUtil.makeId();
//            String passWord = "qqqqq";
//            String personId = "34080319980516235X";
//            String userName = "user000" + i;
//            String email = "601789175@qq.com";
//            String address = "some";
//            allowCheckBeanMapper.registerBean(userName, 0, true, "user", "update");
//            accountMapper.register(accountType, userId, UserUtil.getMD5(passWord), personId, userName, email, address, 0);
//        }
    }

    @Test
    public void deteteBean() throws Exception {
//        accountMapper.deleteUser("LoanCreditAccount", "user0000");
//        allowCheckBeanMapper.deleteBean("user0000");
    }
}