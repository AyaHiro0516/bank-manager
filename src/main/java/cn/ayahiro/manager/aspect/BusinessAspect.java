package cn.ayahiro.manager.aspect;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.AjaxResponseBody;
import cn.ayahiro.manager.model.formbean.LoginBean;
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

    @Before(value = "withdrawPointCut() && args(user,amount)", argNames = "user,amount")
    public void withdrawBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起取款, 数额: {}", user.getUserName(), amount);
    }

    @Before(value = "requestLoanPointCut() && args(user,amount)", argNames = "user,amount")
    public void requestLoanBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起借贷, 数额: {}", user.getUserName(), amount);
    }

    @Before(value = "payLoanPointCut() && args(user,amount)", argNames = "user,amount")
    public void payLoanBefore(Account user, double amount) {
        LOGGER.info("用户: {}发起还贷, 数额: {}", user.getUserName(), amount);
    }

    @Before(value = "transferPointCut() && args(fromUser,toUser,amount)", argNames = "fromUser,toUser,amount")
    public void transferBefore(Account fromUser, Account toUser, double amount) {
        LOGGER.info("用户: {}向用户: {}发起转账, 数额: {}", fromUser.getUserName(), toUser.getUserName(), amount);
    }
}
