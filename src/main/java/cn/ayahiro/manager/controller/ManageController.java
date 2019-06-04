package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.ConditionBean;
import cn.ayahiro.manager.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ManageController {

    @Resource(name = "loginService")
    private LoginService loginService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping(path = {"/management"}, method = RequestMethod.GET)
    public String management(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {
        double totalPageNum = Math.ceil((double) loginService.getAllUsers().size() / (double) PAGE_SIZE);
        model.addAttribute("accountList", loginService.getUsersByPage((pageNum - 1) * PAGE_SIZE, PAGE_SIZE))
                .addAttribute("nowPageNum", pageNum)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", new ConditionBean(true, true, true, true));
        return "management";
    }

    @RequestMapping(path = {"/refresh_management"}, method = RequestMethod.POST)
    public String management(@RequestParam(value = "chooseType", defaultValue = "") String[] chooseTypes, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {
        ConditionBean conditionBean = new ConditionBean();
        for (String type : chooseTypes) {
            switch (type) {
                case "SA":
                    conditionBean.setIsChooseSA(true);
                    break;
                case "CA":
                    conditionBean.setIsChooseCA(true);
                    break;
                case "LSA":
                    conditionBean.setIsChooseLSA(true);
                    break;
                case "LCA":
                    conditionBean.setIsChooseLCA(true);
                    break;
            }
            System.out.println(type);
        }
        double totalPageNum = Math.ceil((double) loginService.getAllUsers().size() / (double) PAGE_SIZE);
        model.addAttribute("accountList", loginService.getUsersByPage((pageNum - 1) * PAGE_SIZE, PAGE_SIZE))
                .addAttribute("nowPageNum", pageNum)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", conditionBean);
        return "management";
    }

    @ResponseBody
    @RequestMapping(path = {"/findByPage"}, method = RequestMethod.GET)
    public List<Account> findByPage() {
        return loginService.getUsersByPage(2, 2);
    }
}
