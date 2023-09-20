package cn.devcxl.generator.utils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author devcxl
 */
public class GeneratorUtils {

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';


    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] parts = input.split("[_-]");
        StringBuilder result = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            result.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
        }

        return result.toString();
    }

    /**
     * 转驼峰命名法 首字母大写
     *
     * @param input
     * @return
     */
    public static String toUpperCaseCamelCase(String input) {
        String camelCase = toCamelCase(input);
        if (camelCase.isEmpty()) {
            return camelCase;
        }
        return Character.toUpperCase(camelCase.charAt(0)) + camelCase.substring(1);
    }

    /**
     * 转驼峰命名法 首字母小写
     *
     * @param input
     * @return
     */
    public static String toLowerCaseCamelCase(String input) {
        String camelCase = toCamelCase(input);
        if (camelCase.isEmpty()) {
            return camelCase;
        }
        return Character.toLowerCase(camelCase.charAt(0)) + camelCase.substring(1);
    }

    /**
     * 驼峰转下划线命名法
     *
     * @param input
     * @return
     */
    public static String toSnakeCase(String input) {
        AtomicReference<StringBuilder> result = new AtomicReference<>(new StringBuilder());
        input.chars().forEachOrdered(c -> {
            if (Character.isUpperCase(c)) {
                if (result.get().length() > 0) {
                    result.get().append(UNDERSCORE);
                }
                result.get().append(Character.toLowerCase((char) c));
            } else if (c == HYPHEN) {
                result.get().append(UNDERSCORE);
            } else {
                result.get().append((char) c);
            }
        });
        return result.get().toString();
    }

    /**
     * 驼峰转中划线命名法
     *
     * @param input
     * @return
     */
    public static String toKebabCase(String input) {
        return toSnakeCase(input).replace(UNDERSCORE, HYPHEN);
    }


    public static boolean containsString(String[] array, String target) {
        for (String str : array) {
            if (str.equals(target)) {
                return true;
            }
        }
        return false;
    }

}
