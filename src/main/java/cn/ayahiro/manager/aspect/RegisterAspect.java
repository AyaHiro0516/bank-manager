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
@Component("registerAspect")
public class RegisterAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterAspect.class);

    //注册切面
    @Pointcut("execution(* cn.ayahiro.manager.service.RegisterService.register(..)))")
    public void registerPointCut() {
    }
}
