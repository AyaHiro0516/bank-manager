package cn.ayahiro.manager.aspect;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.model.formbean.AjaxResponseBody;
import cn.ayahiro.manager.model.formbean.LoginBean;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author ayahiro
 * @Description:
 * @Create: 2019/6/10
 */

@Aspect
@Component("loginAspect")
public class LoginAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAspect.class);

    //登录验证切面
    @Pointcut("execution(* cn.ayahiro.manager.service.LoginService.checkUserIsAllow(..)))")
    public void checkUserIsAllowPointCut() {
    }

//    //修改失败次数切面
//    @Pointcut("execution(* cn.ayahiro.manager.service.LoginService.upDateMissNum(..)))")
//    public void upDateMissNumPointCut() {
//    }
//
//    //修改锁定状态切面
//    @Pointcut("execution(* cn.ayahiro.manager.service.LoginService.upDateIsAllow(..)))")
//    public void upDateIsAllowPointCut() {
//    }

    @Before(value = "checkUserIsAllowPointCut() && args(loginBean,result)", argNames = "loginBean,result")
    public void loginBefore(LoginBean loginBean, AjaxResponseBody result) {
        LOGGER.info("用户: {}尝试登录", loginBean.getUserName());
    }

    @After(value = "checkUserIsAllowPointCut() && args(loginBean,result)", argNames = "loginBean,result")
    public void loginAfter(LoginBean loginBean, AjaxResponseBody result) {
        LOGGER.info("用户: {}的登录结果为: {}", loginBean.getUserName(), result.getMsg());
    }

    @AfterThrowing(value = "checkUserIsAllowPointCut()", throwing = "ex")
    public void loginAfterThrowing(ATMException ex) {
        LOGGER.warn("用户登录出现异常，原因是: {}", ex.getMessage());
    }
}
