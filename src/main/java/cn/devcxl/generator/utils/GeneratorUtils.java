package cn.devcxl.generator.utils;

import java.util.Arrays;

public class GeneratorUtils {

    /**
     * 校验数组是否包含指定值
     *
     * @param arr         数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 首字母小写
     */
    public static String lowerCaptureName(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

    /**
     * 首字母大写
     */
    public static String upperCaptureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
}
