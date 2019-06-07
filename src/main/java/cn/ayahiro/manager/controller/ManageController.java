package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.formbean.ConditionBean;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageController {

    @Resource(name = "loginService")
    private LoginService loginService;

    @Resource(name = "registerService")
    private RegisterService registerService;

    private static final int PAGE_SIZE = 8;

    //记录当前页数，方便全局处理
    private static int nowPageNum;

    //记录当前展示状态，方便全局处理
    public static ConditionBean conditionBean = new ConditionBean(true, true, true, true);

    /*
    * 修改nowPageNum
    * */
    @RequestMapping(path = {"/management"}, method = RequestMethod.GET)
    public String management(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {
        nowPageNum = pageNum;
        double totalPageNum = Math.ceil((double) loginService.getAllUsersByType(conditionBean).size() / (double) PAGE_SIZE);
        model.addAttribute("accountList", loginService.getUsersByPage((nowPageNum - 1) * PAGE_SIZE, PAGE_SIZE, conditionBean))
                .addAttribute("nowPageNum", nowPageNum)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", conditionBean);
        return "management";
    }

    /*
    * 修改conditionBean，重置nowPageNum为1
    * */
    @RequestMapping(path = {"/refresh_management"}, method = RequestMethod.POST)
    public String refreshManagement(@RequestParam(value = "chooseType", defaultValue = "") String[] chooseTypes, Model model) {
        conditionBean.setIsChooseSA(false)
                .setIsChooseCA(false)
                .setIsChooseLSA(false)
                .setIsChooseLCA(false);

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
            //System.out.println(type);
        }

        nowPageNum = 1;
        double totalPageNum = Math.ceil((double) loginService.getAllUsersByType(conditionBean).size() / (double) PAGE_SIZE);
        List<Account> showAccountList = loginService.getUsersByPage(0, PAGE_SIZE, conditionBean);
        model.addAttribute("accountList", showAccountList)
                .addAttribute("nowPageNum", showAccountList.size() == 0 ? 0 : 1)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", conditionBean);
        return "management";
    }

    /*
    * 删除选中用户，重置nowPageNum为1，回显删除后的数据
    * */
    @RequestMapping(path = {"/delete_management"}, method = RequestMethod.POST)
    public String deleteManagement(@RequestParam(value = "selectStatus", defaultValue = "") int[] selectStatus, Model model) {
        List<Account> accountList = loginService.getUsersByPage((nowPageNum - 1) * PAGE_SIZE, PAGE_SIZE, conditionBean);
        for (int index : selectStatus) {
            Account user = accountList.get(index);
            registerService.deleteUserByUserName(user.getAccountType(), user.getUserName());
            registerService.deleteBeanByUserName(user.getUserName());
        }

        nowPageNum = 1;
        double totalPageNum = Math.ceil((double) loginService.getAllUsersByType(conditionBean).size() / (double) PAGE_SIZE);
        List<Account> showAccountList = loginService.getUsersByPage(0, PAGE_SIZE, conditionBean);
        model.addAttribute("accountList", showAccountList)
                .addAttribute("nowPageNum", showAccountList.size() == 0 ? 0 : 1)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", conditionBean);
        return "management";
    }
}
