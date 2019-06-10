package cn.ayahiro.manager.aspect;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.AjaxResponseBody;
import cn.ayahiro.manager.model.formbean.LoginBean;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author ayahiro
 * @Description:
 * @Create: 2019/6/10
 */

@Aspect
@Component("businessAspect")
public class BusinessAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAspect.class);

    //存款切面
    @Pointcut("execution(* cn.ayahiro.manager.service.BusinessService.deposit(..)))")
    public void depositPointCut() {
    }

    //取款切面
    @Pointcut("execution(* cn.ayahiro.manager.service.BusinessService.withdraw(..)))")
    public void withdrawPointCut() {
    }

    //借贷切面
    @Pointcut("execution(* cn.ayahiro.manager.service.BusinessService.requestLoan(..)))")
    public void requestLoanPointCut() {
    }

    //还贷切面
    @Pointcut("execution(* cn.ayahiro.manager.service.BusinessService.payLoan(..)))")
    public void payLoanPointCut() {
    }

    //转账切面
    @Pointcut("execution(* cn.ayahiro.manager.service.BusinessService.transfer(..)))")
    public void transferPointCut() {
    }

    @Before(value = "depositPointCut() && args(user,amount)", argNames = "user,amount")
    public void depositBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起存款, 数额: {}", user.getUserName(), amount);
    }

    @After(value = "depositPointCut() && args(user,amount)", argNames = "user,amount")
    public void depositAfter(Account user, double amount) {
        LOGGER.info("用户: {}存款成功, 余额: {}", user.getUserName(), user.getBalance());
    }

    @Before(value = "withdrawPointCut() && args(user,amount)", argNames = "user,amount")
    public void withdrawBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起取款, 数额: {}", user.getUserName(), amount);
    }

    @After(value = "withdrawPointCut() && args(user,amount)", argNames = "user,amount")
    public void withdrawAfter(Account user, double amount) {
        LOGGER.info("用户: {}取款成功, 余额: {}", user.getUserName(), user.getBalance());
    }

    @Before(value = "requestLoanPointCut() && args(user,amount)", argNames = "user,amount")
    public void requestLoanBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起借贷, 数额: {}", user.getUserName(), amount);
    }

    @After(value = "requestLoanPointCut() && args(user,amount)", argNames = "user,amount")
    public void requestLoanAfter(Account user, double amount) {
        LOGGER.info("用户: {}借贷成功, 余额: {}", user.getUserName(), user.getBalance());
    }

    @Before(value = "payLoanPointCut() && args(user,amount)", argNames = "user,amount")
    public void payLoanBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起还贷, 数额: {}", user.getUserName(), amount);
    }

    @After(value = "payLoanPointCut() && args(user,amount)", argNames = "user,amount")
    public void payLoanAfter(Account user, double amount) {
        LOGGER.info("用户: {}还贷成功, 余额: {}", user.getUserName(), user.getBalance());
    }

    @Before(value = "transferPointCut() && args(fromUser,toUser,amount)", argNames = "fromUser,toUser,amount")
    public void transferBefore(Account fromUser, Account toUser, double amount) {
        LOGGER.info("用户: {}向用户: {}发起转账, 数额: {}", fromUser.getUserName(), toUser.getUserName(), amount);
    }

    @After(value = "transferPointCut() && args(fromUser,toUser,amount)", argNames = "fromUser,toUser,amount")
    public void transferAfter(Account fromUser, Account toUser, double amount) {
        LOGGER.info("用户: {}转账成功, 余额: {}", fromUser.getUserName(), fromUser.getBalance());
        LOGGER.info("用户: {}接收成功, 余额: {}", toUser.getUserName(), toUser.getBalance());
    }
}
