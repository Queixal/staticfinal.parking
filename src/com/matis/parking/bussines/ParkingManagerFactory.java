package com.matis.parking.bussines;

/**
 *
 * @author MiguelAngel
 */
public class ParkingManagerFactory {
    public static ParkingManager getInstance() {
        return new ParkingManagerBO();
    }
}
