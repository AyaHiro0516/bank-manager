package cn.ayahiro.manager.aspect;

import org.aspectj.lang.annotation.Aspect;
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
}
