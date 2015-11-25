package com.matis.parking;

import com.matis.cache.Database;
import com.matis.parking.server.ParameterFilter;
import com.matis.parking.server.ServerHandler;
import com.sun.net.httpserver.HttpContext;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 *
 * @author MiguelAngel
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        Database.createSchedule();
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        HttpContext context = server.createContext("/parking", new ServerHandler());
        context.getFilters().add(new ParameterFilter());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
