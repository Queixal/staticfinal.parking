package com.matis.parking.persistency;

/**
 *
 * @author MiguelAngel
 */
public class ParkingDAOFactory {
    
     public static ParkingDAO getInstance() {
        return new ParkingDAOImpl();
    }
}
