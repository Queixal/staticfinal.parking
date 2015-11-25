package com.matis.parking.bussines;

import com.matis.exceptions.OperationException;
import com.matis.parking.entities.Day;
import com.matis.parking.entities.GPSData;
import com.matis.parking.entities.Parking;
import com.matis.parking.entities.Vehicle;
import com.matis.parking.persistency.ParkingDAO;
import com.matis.parking.persistency.ParkingDAOFactory;
import com.matis.validation.SemanticError;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import com.matis.utils.DoubleUtils;
import com.matis.utils.IntegerUtils;

/**
 *
 * @author MiguelAngel
 */
public class ParkingManagerBO implements ParkingManager {

    private final ParkingDAO parkingDAO;

    public ParkingManagerBO() {
        parkingDAO = ParkingDAOFactory.getInstance();
    }

    @Override
    public void persist(Parking parking) throws OperationException {
        if (parking.getId() == null) {
            parking.setId(parkingDAO.getSequence());
        }
        parkingDAO.persist(parking);
    }

    @Override
    public List<Parking> get(Parking parking) throws OperationException {
        return parkingDAO.find(parking);
    }

    @Override
    public String parkingsToJson(List<Parking> parkingsInDatabase) {
        StringBuilder message = new StringBuilder("{\"parkings\":[");
        int count = 1;
        for (Parking parkingInDatabase : parkingsInDatabase) {
            message.append("{\"id\":").append("\"").append(parkingInDatabase.getId()).append("\",");
            message.append("\"name\":").append("\"").append(parkingInDatabase.getName()).append("\",");
            message.append("\"openingHour\":").append("\"").append(parkingInDatabase.getOpeningHour()).append("\",");
            message.append("\"closingHour\":").append("\"").append(parkingInDatabase.getClosingHour()).append("\",");
            message.append("\"size\":").append("\"").append(parkingInDatabase.getSize()).append("\",");
            
            String lat = parkingInDatabase.getLocation() != null ? String.valueOf(parkingInDatabase.getLocation().getLatitude()) : "null";
            String lon = parkingInDatabase.getLocation() != null ? String.valueOf(parkingInDatabase.getLocation().getLongitude()) : "null";

            message.append("\"latitude\":").append("\"").append(lat).append("\",");
            message.append("\"longitude\":").append("\"").append(lon).append("\",");
            message.append("\"vehicles\":[");
            int contVehicles = 1;
            for (Vehicle vehicle : parkingInDatabase.getVehiclesInsideParking()) {
                message.append("{\"licensePlate\": \"");
                message.append(vehicle.getLicensePlate());
                message.append("\"}");
                if (contVehicles < parkingInDatabase.getVehiclesInsideParking().size()) {
                    message.append(",");
                    contVehicles++;
                }
            }
            
            message.append("],");
            message.append("\"openingDays\":").append("\"");
            if (parkingInDatabase.getOpeningDays() == null) {
                    message.append(String.valueOf(parkingInDatabase.getOpeningDays()));
            } else {
                int cont = 1;
                for (Day day : parkingInDatabase.getOpeningDays()) {
                    message.append(day.getDayName());
                    if (cont < parkingInDatabase.getOpeningDays().size()) {
                        message.append(";");
                        cont++;
                    }
                }
            }
            message.append("\"}");

            if (count < parkingsInDatabase.size()) {
                message.append(",");
                count++;
            }
        }
        message.append("]}");
        return message.toString();
    }

    @Override
    public Parking parseParking(Map<String, Object> mapping) {

        Parking parsedParking = new Parking();
        parsedParking.setId(IntegerUtils.longValue(mapping.get("id")));
        parsedParking.setName((String) mapping.get("name"));
        parsedParking.setOpeningHour(IntegerUtils.getValue(mapping.get("openingHour")));
        parsedParking.setClosingHour(IntegerUtils.getValue(mapping.get("closingHour")));
        parsedParking.setSize(IntegerUtils.getValue(mapping.get("size")));
        if (mapping.get("latitude") != null && mapping.get("longitude") != null) {
            GPSData gps = new GPSData(DoubleUtils.getValue(mapping.get("latitude")), DoubleUtils.getValue(mapping.get("longitude")));
            parsedParking.setLocation(gps);
        }
        if (mapping.get("openingDays") != null) {
            StringTokenizer tokenized = new StringTokenizer((String) mapping.get("openingDays"), ";");
            Day[] daysChosed = new Day[tokenized.countTokens()];
            int i = 0;
            while (tokenized.hasMoreTokens()) {
                String token = tokenized.nextToken();
                daysChosed[i] = Day.get(Integer.parseInt(token));
                i++;
            }
            if (daysChosed.length > 1) {
                Day[] daysChosedForEnum = new Day[daysChosed.length - 1];
                System.arraycopy(daysChosed, 1, daysChosedForEnum, 0, daysChosed.length - 1);
                parsedParking.setOpeningDays(EnumSet.of(daysChosed[0], daysChosedForEnum));
            } else {
                parsedParking.setOpeningDays(EnumSet.of(daysChosed[0]));
            }
        }
        return parsedParking;
    }
    

    @Override
    public void delete(Parking parkingToDelete) throws OperationException {
        parkingDAO.delete(parkingToDelete);
    }

    @Override
    public String errorsToJson(List<SemanticError> errors) {
        StringBuilder message = new StringBuilder("{\"errors\":[");
        int count = 1;
        for (SemanticError error : errors) {
            message.append("{\"code\":").append("\"").append(error.getErrorCode().getId()).append("\",");
            message.append("\"desc\":").append("\"").append(error.getErrorCode().getMessage()).append("\",");
            message.append("\"attr\":").append("\"").append(error.getAttrName()).append("\"");
            
            message.append("}");

            if (count < errors.size()) {
                message.append(",");
                count++;
            }
        }
        message.append("]}");
        
        return message.toString();
    }
}
