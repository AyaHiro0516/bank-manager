package cn.ayahiro.manager.constants;

public final class RegexConstant {

    //字母开头，允许小写字母和数字，5到16位(用户名规范)
    public static final String USER_NAME_REGEX = "^[a-z][a-z0-9]{4,15}$";

    //字母开头，允许小写字母和数字，5到16位(密码规范)
    public static final String USER_PASSWORD_REGEX = "^[a-z][a-z0-9]{4,15}$";

    //只允许英文字母、数字、下划线、英文句号、以及中划线组成(邮箱规范)
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    //正浮点数或正整数
    public static final String AMOUNT_REGEX = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

    //18位身份证
    public static final String PERSON_ID_REGEX = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    //通过判断文件后缀查看是否是标准的excel
    public static final String FILE_EXCEL_REGEX = ".*(.xlsx|.XLSX)$";

    //通过判断文件后缀查看是否是标准的zip压缩包
    public static final String FILE_ZIP_REGEX = ".*(.zip|.ZIP)$";

}
