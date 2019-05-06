package cn.ayahiro.manager.utils;


import java.util.Random;

public class WebUtils {
    public static long makeId() {
        return new Random().nextInt(99999)+10000;
    }
}
