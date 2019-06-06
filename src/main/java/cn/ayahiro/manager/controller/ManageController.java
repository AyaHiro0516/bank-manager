package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.formbean.ConditionBean;
import cn.ayahiro.manager.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class ManageController {

    @Resource(name = "loginService")
    private LoginService loginService;

    private static final int PAGE_SIZE = 8;

    public static ConditionBean conditionBean = new ConditionBean(true, true, true, true);

    @RequestMapping(path = {"/management"}, method = RequestMethod.GET)
    public String management(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model) {
        double totalPageNum = Math.ceil((double) loginService.getAllUsersByType(conditionBean).size() / (double) PAGE_SIZE);
        model.addAttribute("accountList", loginService.getUsersByPage((pageNum - 1) * PAGE_SIZE, PAGE_SIZE, conditionBean))
                .addAttribute("nowPageNum", pageNum)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", conditionBean);
        return "management";
    }

    @RequestMapping(path = {"/refresh_management"}, method = RequestMethod.POST)
    public String refreshManagement(@RequestParam(value = "chooseType", defaultValue = "") String[] chooseTypes,
                                    @RequestParam(value = "selectStatus", defaultValue = "") String[] selectStatus, Model model) {
        conditionBean.setIsChooseSA(false)
                .setIsChooseCA(false)
                .setIsChooseLSA(false)
                .setIsChooseLCA(false);

        //有选中则执行删除操作，再将删完的数据分页展示
        if (selectStatus.length!=0){
            for (String s: selectStatus){
                System.out.println(s);
            }
        }

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
        }
        double totalPageNum = Math.ceil((double) loginService.getAllUsersByType(conditionBean).size() / (double) PAGE_SIZE);
        int nowPageNum = 1;
        if (conditionBean.isEmpty())
            nowPageNum = 0;
        model.addAttribute("accountList", loginService.getUsersByPage(0, PAGE_SIZE, conditionBean))
                .addAttribute("nowPageNum", nowPageNum)
                .addAttribute("totalPageNum", (int) totalPageNum)
                .addAttribute("conditionBean", conditionBean);
        return "management";
    }
}
