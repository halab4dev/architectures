package com.github.halab4dev.common.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *
 * @author halab
 */
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String string) {
        return !isNotEmpty(string);
    }

    public static boolean isNotEmpty(String string) {
        return org.springframework.util.StringUtils.hasText(string);
    }

    public static boolean allEmpty(String... strings) {
        for (String s : strings) {
            if (!isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static String join(Collection<String> collection, char separator) {
        return org.apache.tomcat.util.buf.StringUtils.join(collection, separator);
    }

    public static boolean match(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
