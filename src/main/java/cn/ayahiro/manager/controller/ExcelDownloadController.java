package cn.ayahiro.manager.controller;

import cn.ayahiro.manager.model.formbean.ConditionBean;
import cn.ayahiro.manager.service.LoginService;
import cn.ayahiro.manager.utils.ExcelUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ExcelDownloadController {
    @Resource(name = "loginService")
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("/doExcelDownload")
    public void doExcelDownload(HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        String fileName = "Account Information";
        try {
            response.setHeader("Content-type", "application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
            // 模板导出Excel
            ExcelUtil.templateExportByUser(response.getOutputStream(), loginService.getUserByUserName(subject.getPrincipal().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/doExcelDownloadByType")
    public void doExcelDownloadByType(HttpServletResponse response) {
        String fileName = "Accounts Information";
        ConditionBean conditionBean = ManageController.conditionBean;
        try {
            response.setHeader("Content-type", "application/vnd.ms-excel");
            // 解决导出文件名中文乱码
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
            // 模板导出Excel
            ExcelUtil.templateExportByUserList(response.getOutputStream(), loginService.getAllUsersByType(conditionBean));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
