package com.quantunm.calculator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: huajun.wu
 * @create: 2020-03-06
 **/
public class PatternUtil {
    static Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");

    /**
     * return true if input is float
     * @param input
     * @return
     */
    public static boolean isFloatNumber(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
