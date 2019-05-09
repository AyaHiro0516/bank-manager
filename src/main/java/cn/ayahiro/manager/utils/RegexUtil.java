package cn.ayahiro.manager.utils;

import cn.ayahiro.manager.constants.RegexConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtil {

    private RegexUtil() {
    }

    public static Boolean userNameValidation(String userName) {
        return validation(RegexConstant.USER_NAME_REGEX, userName);
    }

    public static Boolean passWordValidation(String passWord) {
        return validation(RegexConstant.USER_PASSWORD_REGEX, passWord);
    }

    public static Boolean emailValidation(String email) {
        return validation(RegexConstant.EMAIL_REGEX, email);
    }

    public static Boolean personIdValidation(String personId) {
        return validation(RegexConstant.PERSON_ID_REGEX, personId);
    }

    public static Boolean amountValidation(String amount) {
        return validation(RegexConstant.AMOUNT_REGEX, amount);
    }

    private static Boolean validation(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }
}