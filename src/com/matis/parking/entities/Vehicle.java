package com.matis.parking.entities;

import java.io.Serializable;

/**
 * @author MiguelAngel
 */
public class Vehicle implements Serializable{
    
    private String licensePlate; 

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.licensePlate != null ? this.licensePlate.hashCode() : 0);
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
        final Vehicle other = (Vehicle) obj;
        if ((this.licensePlate == null) ? (other.licensePlate != null) : !this.licensePlate.equals(other.licensePlate)) {
            return false;
        }
        return true;
    }
}
