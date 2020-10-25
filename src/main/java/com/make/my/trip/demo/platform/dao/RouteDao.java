package com.make.my.trip.demo.platform.dao;

import com.make.my.trip.demo.platform.responses.GenericResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDao {

    public GenericResponse shortestRoute(String source, String destination);

}
