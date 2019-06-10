package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.aspect.BusinessAspect;
import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.*;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping(path = {"/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = {"/notLogin"}, method = RequestMethod.GET)
    public String notLogin() {
        return "notlogin";
    }

    @RequestMapping(path = {"/unauthorized"}, method = RequestMethod.GET)
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping(path = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null) {
            LOGGER.info("用户: {}退出系统", subject.getPrincipal());
        }
        subject.logout();
        model.addAttribute("loginBean", new LoginBean())
                .addAttribute("message", new Message());
        return "login";
    }

    @RequestMapping(path = {"/result"}, method = RequestMethod.POST)
    public String loginResult(@ModelAttribute LoginBean loginBean, Model model, HttpServletResponse response) {
        Account user = loginService.getUserByNameAndPassWord(loginBean.getUserName(), loginBean.getPassWord());
        model.addAttribute("businessBean", new BusinessBean())
                .addAttribute("user", user)
                .addAttribute("message", new Message());
        return "business";
    }

    @ResponseBody
    @PostMapping("/checkUserIsAllow")
    public ResponseEntity<AjaxResponseBody> checkUserIsAllowByAjax(@RequestBody LoginBean loginBean) {
        AjaxResponseBody result = new AjaxResponseBody();
        String userName = loginBean.getUserName();
        String passWord = loginBean.getPassWord();
        try {
            loginService.checkUserIsAllow(loginBean, result);
            if ("success".equals(result.getMsg())) {
                //此处象征性进行shiro验证
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userName, UserUtil.getMD5(passWord));
                subject.login(token);
            }
        } catch (ATMException e) {
            //e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

}
