package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.FormBean;
import cn.ayahiro.manager.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class LoginController {
    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping(path = {"/login"})
    public String login(Model model){
        FormBean formBean=new FormBean();
        model.addAttribute("formBean", formBean);
        return "login";
    }

    @RequestMapping(path = {"/result"})
    public String result(@ModelAttribute FormBean formBean, Model model){
        Account user=loginService.findUser(formBean.getUserName(), formBean.getPassWord());
        model.addAttribute("user", user);
        return "result";
    }

}
