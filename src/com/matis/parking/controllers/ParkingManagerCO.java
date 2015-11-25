package com.matis.parking.controllers;

import com.matis.exceptions.OperationException;
import com.matis.parking.bussines.ParkingManager;
import com.matis.parking.bussines.ParkingManagerFactory;
import com.matis.parking.entities.Day;
import com.matis.parking.entities.Parking;
import com.matis.parking.entities.Vehicle;
import com.matis.parking.server.Code;
import com.matis.parking.server.Messages;
import com.matis.parking.server.entities.Response;
import com.matis.validation.ItemToValidate;
import com.matis.validation.SemanticError;
import com.matis.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MiguelAngel
 */
public class ParkingManagerCO {

    private final ParkingManager manager;

    public ParkingManagerCO() {
        manager = ParkingManagerFactory.getInstance();
    }

    public Response persist(Map params) {
        Map<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("name", params.get("name"));
        mapping.put("openingHour", params.get("openingHour"));
        mapping.put("closingHour", params.get("closingHour"));
        mapping.put("size", params.get("size"));
        mapping.put("latitude", params.get("latitude"));
        mapping.put("longitude", params.get("longitude"));
        mapping.put("openingDays", params.get("openingDays"));

        Validator validator = new Validator();
        List<ItemToValidate> items = new ArrayList<ItemToValidate>();
        items.add(new ItemToValidate(String.class.getSimpleName(), "name", true, params.get("name")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "openingHour", false, params.get("openingHour")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "closingHour", false, params.get("closingHour")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "size", true, params.get("size")));
        items.add(new ItemToValidate(Double.class.getSimpleName(), "latitude", false, params.get("latitude")));
        items.add(new ItemToValidate(Double.class.getSimpleName(), "longitude", false, params.get("longitude")));
        items.add(new ItemToValidate(Day.class.getSimpleName(), "openingDays", false, params.get("openingDays")));
        List<SemanticError> errors = validator.validate(items);

        Response response = new Response();

        if (errors.isEmpty()) {
            Parking parking = manager.parseParking(mapping);
            try {
                List<Parking> parkingsInDatabase = manager.get(parking);
                if (parkingsInDatabase.isEmpty()) {
                    manager.persist(parking);
                    response.setCode(Code.OK.getCode());
                    parkingsInDatabase.add(parking);
                    String json = manager.parkingsToJson(parkingsInDatabase);
                    response.setMessage(json);
                } else {
                    response.setCode(Code.OK.getCode());
                    String json = manager.parkingsToJson(new ArrayList());
                    response.setMessage(json);
                }

            } catch (OperationException ex) {
                response.setCode(Code.BAD_REQUEST.getCode());
                response.setMessage(Messages.SOMETHING_HAPPENED);
            }
        } else {
            response.setCode(Code.OK.getCode());
            response.setMessage(manager.errorsToJson(errors));
        }
        return response;
    }

    public Response search(Map params) {
        Map<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("id", params.get("id"));
        mapping.put("name", params.get("name"));
        mapping.put("openingHour", params.get("openingHour"));
        mapping.put("closingHour", params.get("closingHour"));
        mapping.put("size", params.get("size"));
        mapping.put("latitude", params.get("latitude"));
        mapping.put("longitude", params.get("longitude"));
        mapping.put("openingDays", params.get("openingDays"));

        Validator validator = new Validator();
        List<ItemToValidate> items = new ArrayList<ItemToValidate>();
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "id", false, params.get("id")));
        items.add(new ItemToValidate(String.class.getSimpleName(), "name", false, params.get("name")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "openingHour", false, params.get("openingHour")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "closingHour", false, params.get("closingHour")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "size", false, params.get("size")));
        items.add(new ItemToValidate(Double.class.getSimpleName(), "latitude", false, params.get("latitude")));
        items.add(new ItemToValidate(Double.class.getSimpleName(), "longitude", false, params.get("longitude")));
        items.add(new ItemToValidate(Day.class.getSimpleName(), "openingDays", false, params.get("openingDays")));
        List<SemanticError> errors = validator.validate(items);

        Response response = new Response();

        if (errors.isEmpty()) {
            Parking parking = manager.parseParking(mapping);
            try {
                List<Parking> parkingsInDatabase = manager.get(parking);
                List<Parking> parkingsToShow = new ArrayList();
                if (params.containsKey("completo") && !Boolean.valueOf("completo")) {
                    for (Parking parkingToShow : parkingsInDatabase) {
                        if (parkingToShow.getVehiclesInsideParking().size() < parkingToShow.getSize()) {
                            parkingsToShow.add(parkingToShow);
                        }
                    }
                }

                response.setCode(Code.OK.getCode());
                String json = manager.parkingsToJson(parkingsInDatabase);
                response.setMessage(json);
            } catch (OperationException ex) {
                response.setCode(Code.BAD_REQUEST.getCode());
                response.setMessage(Messages.SOMETHING_HAPPENED);
            }
        } else {
            response.setCode(Code.OK.getCode());
            response.setMessage(manager.errorsToJson(errors));
        }

        return response;
    }

    public Response delete(Map params) {
        Map<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("id", params.get("id"));

        Validator validator = new Validator();
        List<ItemToValidate> items = new ArrayList<ItemToValidate>();
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "id", false, params.get("id")));
        List<SemanticError> errors = validator.validate(items);
        Response response = new Response();

        if (errors.isEmpty()) {
            try {
                Parking parking = manager.parseParking(mapping);
                List<Parking> parkingsInDatabase = manager.get(parking);
                if (!parkingsInDatabase.isEmpty()) {
                    Parking parkingToDelete = parkingsInDatabase.get(0);
                    manager.delete(parkingToDelete);
                }

                String json = manager.parkingsToJson(parkingsInDatabase);
                response.setCode(Code.OK.getCode());
                response.setMessage(json);
            } catch (OperationException ex) {
                response.setCode(Code.BAD_REQUEST.getCode());
                response.setMessage(Messages.SOMETHING_HAPPENED);
            }
        } else {
            response.setCode(Code.OK.getCode());
            response.setMessage(manager.errorsToJson(errors));
        }

        return response;
    }

    public Response update(Map params) {
        Map<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("id", params.get("id"));
        mapping.put("name", params.get("name"));
        mapping.put("openingHour", params.get("openingHour"));
        mapping.put("closingHour", params.get("closingHour"));
        mapping.put("size", params.get("size"));
        mapping.put("latitude", params.get("latitude"));
        mapping.put("longitude", params.get("longitude"));
        mapping.put("openingDays", params.get("openingDays"));
        mapping.put("addCar", params.get("addCar"));
        mapping.put("removeCar", params.get("removeCar"));
        Validator validator = new Validator();
        List<ItemToValidate> items = new ArrayList<ItemToValidate>();
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "id", true, params.get("id")));
        items.add(new ItemToValidate(String.class.getSimpleName(), "name", false, params.get("name")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "openingHour", false, params.get("openingHour")));
        items.add(new ItemToValidate(Integer.class.getSimpleName(), "closingHour", false, params.get("closingHour")));
        items.add(new ItemToValidate(Double.class.getSimpleName(), "latitude", false, params.get("latitude")));
        items.add(new ItemToValidate(Double.class.getSimpleName(), "longitude", false, params.get("longitude")));
        items.add(new ItemToValidate(Day.class.getSimpleName(), "openingDays", false, params.get("openingDays")));
        if (params.containsKey("addCar")) {
            items.add(new ItemToValidate(String.class.getSimpleName(), "addCar", true, params.get("addCar")));
        } else if (params.containsKey("removeCar")) {
            items.add(new ItemToValidate(String.class.getSimpleName(), "removeCar", true, params.get("removeCar")));
        } else {
            items.add(new ItemToValidate(Integer.class.getSimpleName(), "size", true, params.get("size")));
        }
        List<SemanticError> errors = validator.validate(items);

        Response response = new Response();

        if (errors.isEmpty()) {
            Parking parking = manager.parseParking(mapping);
            try {
                Parking parkingToFind = new Parking();
                parkingToFind.setId(parking.getId());
                List<Parking> results = manager.get(parkingToFind);
                if (!results.isEmpty()) {
                    if (params.containsKey("addCar")) {
                        parking = results.get(0);
                        List<Vehicle> carsInside = parking.getVehiclesInsideParking();
                        if (carsInside.size() < parking.getSize()) {
                            carsInside.add(new Vehicle((String)params.get("addCar")));
                        }
                    } else if(params.containsKey("removeCar")) {
                        parking = results.get(0);
                        List<Vehicle> carsInside = parking.getVehiclesInsideParking();
                        if (carsInside != null) {
                            carsInside.remove(new Vehicle((String)params.get("removeCar")));
                        }
                    } else {
                        manager.persist(parking);
                    }
                    response.setCode(Code.OK.getCode());
                    results.clear();
                    results.add(parking);
                    String json = manager.parkingsToJson(results);
                    response.setMessage(json);
                } else {
                    response.setCode(Code.OK.getCode());
                    String json = manager.parkingsToJson(new ArrayList());
                    response.setMessage(json);
                }

            } catch (OperationException ex) {
                response.setCode(Code.BAD_REQUEST.getCode());
                response.setMessage(Messages.SOMETHING_HAPPENED);
            }
        } else {
            response.setCode(Code.OK.getCode());
            response.setMessage(manager.errorsToJson(errors));
        }
        return response;
    }

    public Response notValidRequest() {
        Response response = new Response();
        response.setCode(Code.OK.getCode());
        response.setMessage(Code.BAD_REQUEST.getMessage());
        return response;
    }

}
