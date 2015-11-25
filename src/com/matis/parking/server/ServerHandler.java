package com.matis.parking.server;

import com.matis.parking.controllers.ParkingManagerCO;
import com.matis.parking.server.entities.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 *
 * @author MiguelAngel
 */
public class ServerHandler implements HttpHandler {

    private ParkingManagerCO parkingController;

    public ServerHandler() {
        parkingController = new ParkingManagerCO();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map params = (Map) exchange.getAttribute("parameters");
        Response response;
        //SEARCH
        if ("get".equalsIgnoreCase(exchange.getRequestMethod())) {
            response = parkingController.search(params);
        //INSERT
        } else if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
            response = parkingController.persist(params);
        //DELETE
        } else if ("delete".equalsIgnoreCase(exchange.getRequestMethod())) {
            response = parkingController.delete(params);
        //UPDATE 
        } else if ("put".equalsIgnoreCase(exchange.getRequestMethod())) {
            System.out.println(params);
            response = parkingController.update(params);
        } else {
            response = parkingController.notValidRequest();
        }

        exchange.sendResponseHeaders( response.getCode(), response.getMessage().length());
        OutputStream os = exchange.getResponseBody();
        os.write( response.getMessage().getBytes());
        os.close();
    }

}
