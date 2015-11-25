package com.matis.cache;

import com.matis.parking.entities.Day;
import com.matis.parking.entities.GPSData;
import com.matis.parking.entities.Parking;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import com.matis.utils.DateUtils;

/**
 *
 * @author MiguelAngel
 */
public class Database {
    
    public static Map<Long, Parking> parkingsPersisted = new HashMap<Long, Parking>();
    
    public static void createSchedule() {
        Parking tmpUnpersistedParking = new Parking();
        tmpUnpersistedParking.setName("Garatge La Gleva");
        GPSData gps = new GPSData(41.3850639, 2.1734035);
        EnumSet<Day> days = EnumSet.of(Day.SUNDAY, Day.MONDAY,
                Day.SATURDAY, Day.SUNDAY, Day.THURSDAY);
        
        tmpUnpersistedParking.setId(0L);
        tmpUnpersistedParking.setSize(9);
        tmpUnpersistedParking.setOpeningDays(days);
        tmpUnpersistedParking.setOpeningHour(DateUtils.transformHHMMToInt("15:30"));
        tmpUnpersistedParking.setClosingHour(DateUtils.transformHHMMToInt("20:30"));
        tmpUnpersistedParking.setLocation(gps);
        parkingsPersisted.put(tmpUnpersistedParking.getId(), tmpUnpersistedParking);
        
        tmpUnpersistedParking = new Parking();
        tmpUnpersistedParking.setId(1L);
        tmpUnpersistedParking.setSize(20);
        tmpUnpersistedParking.setName("Donato Moreno Gonzalez");
        tmpUnpersistedParking.setOpeningDays(days);
        tmpUnpersistedParking.setOpeningHour(DateUtils.transformHHMMToInt("08:30"));
        tmpUnpersistedParking.setClosingHour(DateUtils.transformHHMMToInt("12:30"));
        tmpUnpersistedParking.setLocation(gps);
        parkingsPersisted.put(tmpUnpersistedParking.getId(), tmpUnpersistedParking);
        
        tmpUnpersistedParking = new Parking();
        tmpUnpersistedParking.setId(2L);
        tmpUnpersistedParking.setSize(3);
        tmpUnpersistedParking.setName("Corver");
        tmpUnpersistedParking.setOpeningDays(days);
        tmpUnpersistedParking.setOpeningHour(DateUtils.transformHHMMToInt("15:30"));
        tmpUnpersistedParking.setClosingHour(DateUtils.transformHHMMToInt("23:30"));
        tmpUnpersistedParking.setLocation(gps);
        parkingsPersisted.put(tmpUnpersistedParking.getId(), tmpUnpersistedParking);
    }
}
