package com.matis.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 *
 * @author MiguelAngel
 */
public class DateUtils {

    public static String transformIntToHHMM(int hhmm) {
        final int hours = hhmm / 60;
        final int remainMinute = (hhmm % 60) / 60;
        return String.format("%02d", hours) + ":"
                + String.format("%02d", remainMinute);
    }

    public static int transformHHMMToInt(String hhmm) {
        final StringTokenizer tokenizer = new StringTokenizer(hhmm, ":");
        int response = 0;
        for (int i = 0; tokenizer.hasMoreTokens(); i++) {
            String token = tokenizer.nextToken();
            switch (i) {
                //HOURS
                case 0:
                    response = Integer.parseInt(token) * 60;
                    break;
                //MINUTES
                case 1:
                    response += Integer.parseInt(token);
                    break;
                default:
                    response = 0;
            }
        }
        return response;
    }

    public static boolean isValidFormat(String date, String format) {
        DateFormat f = new SimpleDateFormat(format);
        try {
            f.parse(date);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}
