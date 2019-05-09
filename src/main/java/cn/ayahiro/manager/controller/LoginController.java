package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.BusinessBean;
import cn.ayahiro.manager.model.formbean.LoginBean;
import cn.ayahiro.manager.model.formbean.Message;
import cn.ayahiro.manager.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class LoginController {
    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping(path = {"/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginBean", new LoginBean())
                .addAttribute("message", new Message());
        return "login";
    }

    @RequestMapping(path = {"/login/result"}, method = RequestMethod.POST)
    public String loginResult(@ModelAttribute LoginBean loginBean, Model model) {
        Account user = loginService.getUserByNameAndPassWord(loginBean.getUserName(), loginBean.getPassWord());
        Message message = new Message();
        if (user == null) {
            message.setStatus(false).setInfo("Wrong username or password!");
            model.addAttribute("message", message);
            return "login";
        }
        //message.setStatus(false).setInfo("from /result");
        BusinessBean businessBean = new BusinessBean();
        model.addAttribute("businessBean", businessBean)
                .addAttribute("user", user)
                .addAttribute("message", message);
        return "business";
    }

}
