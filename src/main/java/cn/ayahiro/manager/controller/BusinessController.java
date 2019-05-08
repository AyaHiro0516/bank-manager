package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.BusinessBean;
import cn.ayahiro.manager.model.formbean.Message;
import cn.ayahiro.manager.service.BusinessService;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.service.RegisterService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
public class BusinessController {
    @Resource(name = "loginService")
    private LoginService loginService;

    @Resource(name = "registerService")
    private RegisterService registerService;

    @Resource(name = "businessService")
    private BusinessService businessService;

    @RequestMapping(path = {"/do_business"}, method = RequestMethod.POST)
    public String doBusiness(@ModelAttribute BusinessBean businessBean, Model model){
        if (!registerService.checkBusinessBean(businessBean)){
            Account user=loginService.getUserByName(businessBean.getFromName());
            model.addAttribute("message", new Message(false, "Incorrect input, re-enter, please."))
                    .addAttribute("businessBean", businessBean)
                    .addAttribute("user", user);
            System.out.println(businessBean.getError().toString());
            return "business";
        }

        return "index";
    }
}
