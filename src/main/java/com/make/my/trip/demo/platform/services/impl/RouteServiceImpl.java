package com.user.ledger.platform.services.impl;

import com.user.ledger.platform.dao.RouteDao;
import com.user.ledger.platform.responses.GenericResponse;
import com.user.ledger.platform.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
