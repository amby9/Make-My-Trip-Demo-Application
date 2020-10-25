package com.make.my.trip.demo.platform.enums;

import org.apache.http.HttpStatus;

public class Constants {

    // HTTP Status Codes
    public static final int HTTP_CODE_OK = HttpStatus.SC_OK;
    public static final int HTTP_CREATED = HttpStatus.SC_CREATED;
    public static final int HTTP_CODE_BAD_REQUEST = HttpStatus.SC_BAD_REQUEST;
    public static final int HTTP_CODE_UNAUTHORIZED = HttpStatus.SC_UNAUTHORIZED;
    public static final int HTTP_CODE_INTERNAL_SERVER_ERROR = HttpStatus.SC_INTERNAL_SERVER_ERROR;
    public static final int HTTP_CODE_SERVICE_NOT_AVAILABLE = HttpStatus.SC_SERVICE_UNAVAILABLE;

    // HTTP Status Code Names
    public static final String HTTP_STATUS_OK = "Success";
    public static final String HTTP_STATUS_CREATED = "Created";
    public static final String HTTP_STATUS_BAD_REQUEST = "Bad Request";
    public static final String HTTP_STATUS_UNAUTHORIZED = "Unauthorized";
    public static final String HTTP_STATUS_INTERNAL_SERVER_ERROR = "Internal Server Error";

}
