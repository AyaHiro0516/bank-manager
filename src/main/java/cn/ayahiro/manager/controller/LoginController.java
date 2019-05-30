package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.*;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
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
    public ResponseEntity<AjaxResponseBody> getSearchResultViaAjax(@RequestBody LoginBean loginBean) {
        AjaxResponseBody result = new AjaxResponseBody();
        String userName = loginBean.getUserName();
        String passWord = loginBean.getPassWord();
        Account user = loginService.getUserByNameAndPassWord(userName, passWord);
        AllowCheckBean checkBean = loginService.getBeanByUserName(userName);
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
            if (checkBean.isAllow() && checkBean.getMissNum() < 5) {
                result.setMsg("success");

                //此处象征性进行shiro验证
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token=new UsernamePasswordToken(userName, UserUtil.getMD5(passWord));
                subject.login(token);

            } else if (!checkBean.isAllow()) {
                result.setMsg("Today's login opportunity has been used up.");
            }
        }
        return ResponseEntity.ok(result);
    }

}
