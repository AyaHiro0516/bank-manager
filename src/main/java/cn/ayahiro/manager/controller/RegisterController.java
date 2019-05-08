package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class RegisterController {
    @Resource(name = "registerService")
    private RegisterService registerService;

    @RequestMapping(path = {"/register"}, method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(path = {"/isRegister"}, method = RequestMethod.GET)
    @ResponseBody
    public void isRegister(){
        System.out.println(registerService.isRegister("dasdas"));
    }
}
