package com.matis.parking.persistency;

import com.matis.parking.entities.Parking;
import java.util.List;

/**
 *
 * @author MiguelAngel
 */
public interface ParkingDAO {
    
    public List<Parking> find(Parking parking);
    
    public Long getSequence();
    
    public void persist(Parking parkingToPersist);

    public void delete(Parking parkingToDelete);
}
