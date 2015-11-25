package com.matis.utils;

/**
 *
 * @author MiguelAngel
 */
public class StringUtils {
    
    public static boolean isBlank (String str) {
        return str.trim().isEmpty();
    } 
    
    public static boolean isNull (String str) {
        return str == null;
    } 
}
