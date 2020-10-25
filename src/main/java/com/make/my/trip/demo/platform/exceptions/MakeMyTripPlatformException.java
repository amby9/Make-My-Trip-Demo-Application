package com.make.my.trip.demo.platform.exceptions;

import com.make.my.trip.demo.platform.enums.ErrorCodes;

public class MakeMyTripPlatformException extends RuntimeException {
    private final int status;
    private final String message;
    private String reason;
    private String errorCode;

    public MakeMyTripPlatformException(int status, String message, String reason) {
        super(message);
        this.status = status;
        this.message = message;
        this.reason = reason!=null? reason:"";
    }

    public MakeMyTripPlatformException(int status, ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.status = status;
        this.message = errorCode.getMessage();
        this.reason = errorCode.getReason();
        this.errorCode = errorCode.name();
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getReason(){return reason;}
}
