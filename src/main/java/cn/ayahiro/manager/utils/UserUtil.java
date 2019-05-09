package cn.ayahiro.manager.utils;


import org.springframework.util.DigestUtils;

import java.util.Random;

public class UserUtil {
    public static long makeId() {
        return new Random().nextInt(99999) + 10000;
    }

    public static String getMD5(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }
}
