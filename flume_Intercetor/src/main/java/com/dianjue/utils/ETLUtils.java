package com.dianjue.utils;

import org.apache.commons.lang.math.NumberUtils;

public class ETLUtils {

    public static boolean startOption(String log) {

        return log.startsWith("{") && log.endsWith("}");
    }
    public static boolean logOption(String log) {
        String[] actionlog = log.split("\\|");
        //判断字符串中是否全为数字
        if(actionlog[0].trim().length() != 13 || !NumberUtils.isDigits(actionlog[0].trim())){
            return false;
        }else{
            return actionlog[1].trim().startsWith("{") && actionlog[1].trim().endsWith("}");
        }
    }
}
