package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ManageController {

    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping(path = {"/management"}, method = RequestMethod.GET)
    public String management(Model model) {
        model.addAttribute("accountList", loginService.getUsersByPage(1, 8));
        return "management";
    }
    @ResponseBody
    @RequestMapping(path = {"/findByPage"}, method = RequestMethod.GET)
    public List<Account> findByPage() {
        return loginService.getUsersByPage(2, 2);
    }
}
