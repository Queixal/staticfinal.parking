package com.matis.parking.entities;

import com.matis.parking.server.Messages;

/**
 *
 * @author MiguelAngel
 */
public enum Day {

    SUNDAY(0, Messages.SUNDAY),
    MONDAY(1, Messages.MONDAY),
    TUESDAY(2, Messages.TUESDAY),
    WEDNESDAY(3, Messages.WEDNESDAY),
    THURSDAY(4, Messages.THURSDAY),
    FRIDAY(5, Messages.FRIDAY),
    SATURDAY(6, Messages.SATURDAY);

    public static boolean exist(int day) {
        switch (day) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    public static Day get(int dayChosed) {
        if (exist(dayChosed)) {
            for (Day day : Day.values()) {
                if (day.getDayNumber() == dayChosed) {
                    return day;
                }
            }
        }
        return null;
    }

    private int dayNumber;
    private String dayName;

    private Day(int dayNumber, String dayName) {
        this.dayNumber = dayNumber;
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

}
