package com.make.my.trip.demo.platform.services;

import com.make.my.trip.demo.platform.responses.GenericResponse;
import org.springframework.stereotype.Component;

@Component
public interface RouteService {

    public GenericResponse shortestRoute(String source, String destination);

}
