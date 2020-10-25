package com.user.ledger.platform.services;

import com.user.ledger.platform.responses.GenericResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RouteService {

    public GenericResponse shortestRoute(String source, String destination);

}
