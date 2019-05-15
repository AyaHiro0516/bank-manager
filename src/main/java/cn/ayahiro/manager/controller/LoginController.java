package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.*;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.utils.RegexUtil;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @RequestMapping(path = {"/result"}, method = RequestMethod.POST)
    public String loginResult(@ModelAttribute LoginBean loginBean, Model model) {
        Account user = loginService.getUserByNameAndPassWord(loginBean.getUserName(), loginBean.getPassWord());
        //System.out.println(user.toString());
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

    @ResponseBody
    @PostMapping("/checkUserIsAllow")
    public ResponseEntity<?> getSearchResultViaAjax(@RequestBody LoginBean loginBean) {
        AjaxResponseBody result = new AjaxResponseBody();
        String userName = loginBean.getUserName();
        String passWord = loginBean.getPassWord();
        Account user = loginService.getUserByNameAndPassWord(userName, passWord);
        if (user == null) {
            result.setMsg("Wrong username or password!");
        } else {
            result.setMsg("success");
            result.setResult(user);
        }
        return ResponseEntity.ok(result);
    }
}
