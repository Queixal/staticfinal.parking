package com.matis.parking.bussines;

import com.matis.exceptions.OperationException;
import com.matis.parking.entities.Parking;
import com.matis.validation.SemanticError;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MiguelAngel
 */
public interface ParkingManager {
    public void persist(Parking parking) throws OperationException;
    public List<Parking> get(Parking parking) throws OperationException;
    public void delete(Parking parkingToDelete) throws OperationException;
    public String parkingsToJson(List<Parking> parkingsInDatabase);
    public String errorsToJson(List<SemanticError> parkingsInDatabase);

    public Parking parseParking(Map<String, Object> mapping);
}
