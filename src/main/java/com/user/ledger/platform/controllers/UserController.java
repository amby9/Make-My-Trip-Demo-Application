package com.user.ledger.platform.controllers;

import com.user.ledger.platform.requests.UserNoteRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import com.user.ledger.platform.enums.Constants;
import com.user.ledger.platform.requests.UserRegistrationOrLoginRequest;
import com.user.ledger.platform.responses.GenericResponse;
import com.user.ledger.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CREATED, message = Constants.HTTP_STATUS_CREATED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/app/user", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationOrLoginRequest userRegistrationOrLoginRequest) {
        GenericResponse genericResponse = userService.registerUser(userRegistrationOrLoginRequest);
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/app/user/auth", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody UserRegistrationOrLoginRequest userRegistrationOrLoginRequest) {
        GenericResponse genericResponse = userService.loginUser(userRegistrationOrLoginRequest);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/app/sites/list", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNotes(@RequestParam String userId) {
        List<String> userNotes = userService.getUserNotes(userId);
        return new ResponseEntity<>(userNotes, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = Constants.HTTP_CODE_OK, message = Constants.HTTP_STATUS_OK, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_BAD_REQUEST, message = Constants.HTTP_STATUS_BAD_REQUEST, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_UNAUTHORIZED, message = Constants.HTTP_STATUS_UNAUTHORIZED, response = Object.class),
            @ApiResponse(code = Constants.HTTP_CODE_INTERNAL_SERVER_ERROR, message = Constants.HTTP_STATUS_INTERNAL_SERVER_ERROR, response = Object.class)})
    @RequestMapping(value = "/app/sites", method = RequestMethod.POST)
    public ResponseEntity<?> addUserNote(@RequestParam String userId, @RequestBody UserNoteRequest userNoteRequest) {
        GenericResponse genericResponse = userService.addUserNote(userId, userNoteRequest);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
