package cn.ayahiro.manager.aspect;

import cn.ayahiro.manager.model.formbean.AjaxResponseBody;
import cn.ayahiro.manager.model.formbean.LoginBean;
import cn.ayahiro.manager.model.formbean.RegisterBean;
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
@Component("registerAspect")
public class RegisterAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterAspect.class);

    //注册切面
    @Pointcut("execution(* cn.ayahiro.manager.service.RegisterService.register(..)))")
    public void registerPointCut() {
    }

    @After(value = "registerPointCut() && args(registerBean)", argNames = "registerBean")
    public void registerAfter(RegisterBean registerBean) {
        LOGGER.info("用户: {}注册成功", registerBean.getUserName());
    }
}
