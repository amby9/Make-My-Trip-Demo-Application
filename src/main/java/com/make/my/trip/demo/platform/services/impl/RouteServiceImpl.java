package com.make.my.trip.demo.platform.services.impl;

import com.make.my.trip.demo.platform.responses.GenericResponse;
import com.make.my.trip.demo.platform.dao.RouteDao;
import com.make.my.trip.demo.platform.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteDao routeDao;

    @Autowired
    public RouteServiceImpl(RouteDao routeDao){
        this.routeDao = routeDao;
    }

    public GenericResponse shortestRoute(String source, String destination){
        return routeDao.shortestRoute(source, destination);
    }

}
