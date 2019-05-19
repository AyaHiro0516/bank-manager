package cn.ayahiro.manager.task;

import cn.ayahiro.manager.mapper.AllowCheckBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("isAllowCleanTask")
public class IsAllowCleanTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsAllowCleanTask.class);

    @Resource(name = "allowCheckBeanMapper")
    private AllowCheckBeanMapper allowCheckBeanMapper;

    //每天2点重置用户登录状态
    @Scheduled(cron = "0 0 2 * * ?")
    public void isAllowClean() {
        allowCheckBeanMapper.upDateAllMissNum(0);
        allowCheckBeanMapper.upDateAllIsAllow(true);
    }
}
