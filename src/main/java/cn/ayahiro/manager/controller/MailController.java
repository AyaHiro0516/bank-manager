package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;

@Controller
public class MailController {
    @Resource(name = "mailService")
    private MailService mailService;

    @ResponseBody
    @PostMapping("/sendEmailCaptcha")
    public String getCheckCode(@RequestParam(value = "email", required = true) String email) {
        String emailCaptcha = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为：" + emailCaptcha;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        } catch (Exception e) {
            return "";
        }
        return emailCaptcha;
    }
}
