package com.matis.utils;

import java.util.regex.Pattern;

/**
 *
 * @author MiguelAngel
 */
public class IntegerUtils {
    
    public static boolean isInteger(Object value){
        return Pattern.matches("^\\d+$", String.valueOf(value));
    }
    
    
    public static Integer getValue(Object value){
        if (!isNull(value) && isInteger(value)) {
            return Integer.valueOf(String.valueOf(value));
        }
        return null;
    }
    
    public static boolean isNull(Object value){
        return value == null;
    }

    public static Long longValue(Object value) {
         if (!isNull(value) && isInteger(value)) {
            return Long.valueOf(String.valueOf(value));
        }
         return null;
    }
}
