package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.exceptions.ATMException;
import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.AjaxResponseBody;
import cn.ayahiro.manager.model.formbean.BusinessBean;
import cn.ayahiro.manager.model.formbean.Message;
import cn.ayahiro.manager.service.BusinessService;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String doBusiness(@ModelAttribute BusinessBean businessBean, Model model) {
        Account user = loginService.getUserByUserName(businessBean.getFromName());
        if (!registerService.checkBusinessBean(businessBean)) {
            model.addAttribute("message", new Message(false, "Incorrect input, re-enter, please."))
                    .addAttribute("businessBean", businessBean)
                    .addAttribute("user", user);
            //System.out.println(businessBean.getError().toString());
            return "business";
        }

        Double amount = new Double(businessBean.getAmount());
        Message message = new Message();
        message.setInfo("Success!");
        try {
            switch (businessBean.getMode()) {
                case "Deposit":
                    businessService.deposit(user, amount);
                    break;
                case "Withdrawal":
                    businessService.withdraw(user, amount);
                    break;
                case "Request Loan":
                    businessService.requestLoan(user, amount);
                    break;
                case "Pay Loan":
                    businessService.payLoan(user, amount);
                    break;
                case "Transfer":
                    Account toUser = loginService.getUserByUserName(businessBean.getToName());
                    businessService.transfer(user, toUser, amount);
                    break;
            }
        } catch (ATMException e) {
            e.printStackTrace();
            message.setInfo(e.getMessage());
        }
        model.addAttribute("message", message)
                .addAttribute("businessBean", businessBean)
                .addAttribute("user", user);
        return "business";
    }

    @ResponseBody
    @PostMapping("/refresh")
    public ResponseEntity<AjaxResponseBody> getSearchResultViaAjax(@RequestBody BusinessBean businessBean) {
        AjaxResponseBody result = new AjaxResponseBody();
        Account user = loginService.getUserByUserName(businessBean.getFromName());
        if (user == null) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
            result.setResult(user);
        }
        System.out.println(user.toString());
        return ResponseEntity.ok(result);
    }
}
