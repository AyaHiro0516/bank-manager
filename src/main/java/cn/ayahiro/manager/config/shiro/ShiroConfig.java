package cn.ayahiro.manager.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/notLogin");
        factoryBean.setUnauthorizedUrl("/unauthorized");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //LoginController
        filterChainDefinitionMap.put("/result", "authc");
        //RegisterController
        filterChainDefinitionMap.put("/do_register", "anon");
        //BusinessController
        filterChainDefinitionMap.put("/do_business", "roles[user]");
        filterChainDefinitionMap.put("/refresh", "perms[update]");
        //ManageController
        filterChainDefinitionMap.put("/management", "roles[admin]");
        //CaptchaController
        filterChainDefinitionMap.put("/kaptcha.jpg", "anon");

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(MyRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(realm);
        return manager;
    }

}
