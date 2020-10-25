package com.make.my.trip.demo.platform.advices;

import com.make.my.trip.demo.platform.exceptions.APIExceptions;
import com.make.my.trip.demo.platform.exceptions.MakeMyTripPlatformException;
import com.make.my.trip.demo.platform.enums.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MakeMyTripPlatformException.class)
    ResponseEntity<APIExceptions> handleAPException(MakeMyTripPlatformException e, HttpServletResponse response) throws IOException {
        log.error("User Ledger Platform Exception occurred: ",e);
        return new ResponseEntity<>(APIExceptions
                .builder()
                .title(e.getMessage())
                .description(e.getReason())
                .type(HttpStatus.valueOf(e.getStatus()).getReasonPhrase())
                .build(), HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    void handleBasicValidation(UnexpectedTypeException e, HttpServletResponse response) throws IOException{
        log.error("UnexpectedTypeException occurred: ",e);
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    // Default Exception Handler for exceptions not caught anywhere else
    @ExceptionHandler({Exception.class})
    void deafultExceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        log.error("Exception occurred::{} ",ExceptionUtils.getStackTrace(e));
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.INTERNAL_SERVER_ERROR.getMessage());
    }
}
