package com.matis.parking.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author MiguelAngel
 */
public class Parking implements Serializable {
    
    private Long id;
    
    //int value must be between 0 and 1439 (the number of minutes in the day)
    //So noon would be represented as 720 (12 * 60); 9:30am would be 570 (9 * 60 + 30),
    //9:30pm would be 1290 (21 * 60 + 30), etc.
    private String name;
    private Integer openingHour;
    private Integer closingHour;
    private Integer size;
    private GPSData location;
    
    private List<Vehicle> vehiclesInsideParking = new ArrayList<Vehicle>();
    private EnumSet <Day> openingDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Integer openingHour) {
        this.openingHour = openingHour;
    }

    public Integer getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(Integer closingHour) {
        this.closingHour = closingHour;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public GPSData getLocation() {
        return location;
    }

    public void setLocation(GPSData location) {
        this.location = location;
    }

    public List<Vehicle> getVehiclesInsideParking() {
        return vehiclesInsideParking;
    }

    public void setVehiclesInsideParking(List<Vehicle> vehiclesInsideParking) {
        this.vehiclesInsideParking = vehiclesInsideParking;
    }

    public EnumSet<Day> getOpeningDays() {
        return openingDays;
    }

    public void setOpeningDays(EnumSet<Day> openingDays) {
        this.openingDays = openingDays;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parking other = (Parking) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
