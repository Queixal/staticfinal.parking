package com.matis.utils;

import java.util.regex.Pattern;

/**
 *
 * @author MiguelAngel
 */
public class DoubleUtils {
    public static boolean isDouble(Object value){
        return Pattern.matches("-?\\\\d+(.\\\\d+)?", String.valueOf(value));
    }
    
    
    public static Double getValue(Object value){
        if (!isNull(value) && isDouble(value)) {
            return Double.valueOf(String.valueOf(value));
        }
        return null;
    }
    
    public static boolean isNull(Object value){
        return value == null;
    }
}
