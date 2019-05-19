package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.*;
import cn.ayahiro.manager.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = {"/result"}, method = RequestMethod.POST)
    public String loginResult(@ModelAttribute LoginBean loginBean, Model model) {
        Account user = loginService.getUserByNameAndPassWord(loginBean.getUserName(), loginBean.getPassWord());
        //System.out.println(user.toString());
        Message message = new Message();
//        if (user == null) {
//            message.setStatus(false).setInfo("Wrong username or password!");
//            model.addAttribute("message", message);
//            return "login";
//        }
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
        AllowCheckBean checkBean = loginService.getBeanByName(userName);
        if (user == null) {
            //1若该用户存在  说明密码错误， update missNum  达到5次  isAllow置为false  missNum清零
            if (checkBean != null && checkBean.isAllow()) {
                if (checkBean.getMissNum() >= 5) {
                    loginService.upDateMissNum(0, userName);
                    loginService.upDateIsAllow(false, userName);
                    result.setMsg("Today's login opportunity has been used up.");
                } else {
                    loginService.upDateMissNum(checkBean.getMissNum() + 1, userName);
                    int chance = 4 - checkBean.getMissNum();
                    if (chance == 0) {
                        result.setMsg("Today's login opportunity has been used up.");
                    } else {
                        result.setMsg("Wrong password! " + chance + " chance(s) left.");
                    }
                }
            } else if (checkBean != null && !checkBean.isAllow()) {
                result.setMsg("Today's login opportunity has been used up.");
            } else {
                //2若该用户不存在  就是不存在  不作操作
                result.setMsg("User does not exist, or empty passWord!");
            }
        } else {
            //此处判断miss_time和isAllow
            // if isAllow (...判断miss_time  少于5次放行) else  (通知用户无法登录)
            if (checkBean.isAllow() && checkBean.getMissNum() < 5) {
                result.setMsg("success");
            } else if (!checkBean.isAllow()) {
                result.setMsg("Today's login opportunity has been used up.");
            }
        }
        return ResponseEntity.ok(result);
    }

}
