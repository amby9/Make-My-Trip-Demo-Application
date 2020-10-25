package com.user.ledger.platform.controllers;

import com.user.ledger.platform.exceptions.APIExceptions;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import com.user.ledger.platform.enums.Constants;
import com.user.ledger.platform.responses.GenericResponse;
import com.user.ledger.platform.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = GenericResponse.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = APIExceptions.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = APIExceptions.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = APIExceptions.class)})
    @RequestMapping(value = "/make-my-trip/route", method = RequestMethod.GET)
    public ResponseEntity<?> getShortestRoute(@RequestParam String source, @RequestParam String destination) {
        GenericResponse genericResponse = routeService.shortestRoute(source, destination);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
