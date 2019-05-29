package cn.ayahiro.manager.config.shiro;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.AllowCheckBean;
import cn.ayahiro.manager.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm {
    @Resource(name = "loginService")
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String) principals.getPrimaryPrincipal();
        AllowCheckBean allowCheckBean = loginService.getBeanByUserName(userName);
        Set<String> role = new HashSet<>(Arrays.asList(allowCheckBean.getRole().split(",")));
        authorizationInfo.addRoles(role);
        Set<String> permission = new HashSet<>(Arrays.asList(allowCheckBean.getPermission().split(",")));
        authorizationInfo.addStringPermissions(permission);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account user = loginService.getUserByUserName(token.getUsername());
        String password = user.getPassWord();
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}
