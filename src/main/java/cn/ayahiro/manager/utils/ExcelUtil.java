package cn.ayahiro.manager.utils;

import cn.ayahiro.manager.model.Account;
import cn.ayahiro.manager.model.CreditAccount;
import cn.ayahiro.manager.model.LoanCreditAccount;
import cn.ayahiro.manager.model.LoanSavingAccount;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class ExcelUtil {
    public static void templateExport(OutputStream out, Account user) throws IOException {
        // 1.读取Excel模板
        File file= ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"static/excel/template.xlsx");
        InputStream in=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(in);
        // 2.读取模板里面的所有Sheet
        XSSFSheet sheet=wb.getSheetAt(0);
        // 3.设置公式自动读取
        sheet.setForceFormulaRecalculation(true);
        // 4.向相应的单元格里面设置值
        XSSFRow row;
        // 5.得到第三行
        row=sheet.getRow(3);
        // 6.设置单元格属性值和样式
        row.getCell(0).setCellValue(user.getUserId());
        row.getCell(1).setCellValue(user.getUserName());
        row.getCell(2).setCellValue(user.getPersonId());
        row.getCell(3).setCellValue(user.getEmail());
        row.getCell(4).setCellValue(user.getAddress());
        row.getCell(5).setCellValue(user.getBalance());
        if (user instanceof CreditAccount){
            row.getCell(6).setCellValue(((CreditAccount) user).getCeiling());
        }
        if (user instanceof LoanCreditAccount){
            row.getCell(6).setCellValue(((LoanCreditAccount) user).getCeiling());
        }
        if (user instanceof LoanSavingAccount){
            row.getCell(7).setCellValue(((LoanSavingAccount) user).getLoan());
        }
        if (user instanceof LoanCreditAccount){
            row.getCell(7).setCellValue(((LoanCreditAccount) user).getLoan());
        }
        row.getCell(8).setCellValue(user.getAccountType());
        // 7.设置sheet名称和单元格内容
        wb.setSheetName(0,"Account Information");
        try
        {
            // 8.将Excel写入到输出流里面
            wb.write(out);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
