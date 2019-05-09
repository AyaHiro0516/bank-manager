package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.formbean.Message;
import cn.ayahiro.manager.model.formbean.RegisterBean;
import cn.ayahiro.manager.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class RegisterController {
    @Resource(name = "registerService")
    private RegisterService registerService;

    @RequestMapping(path = {"/register"}, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registerBean", new RegisterBean())
                .addAttribute("message", new Message());
        return "register";
    }

    @RequestMapping(path = {"/do_register"}, method = RequestMethod.POST)
    public String doRegister(@ModelAttribute RegisterBean registerBean, Model model) {
        System.out.println(registerBean.toString());
        if (!registerService.checkRegisterBean(registerBean)) {
            System.out.println(registerBean.getError().toString());
            model.addAttribute("message", new Message(false, "Incorrect input, re-enter, please."))
                    .addAttribute("registerBean", registerBean);
            return "register";
        }
        Message message = new Message();
        message.setInfo("Success!");
        registerService.register(registerBean);
        model.addAttribute("message", message)
                .addAttribute("registerBean", new RegisterBean());
        return "register";
    }
}
