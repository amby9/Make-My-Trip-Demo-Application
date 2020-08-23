package com.user.ledger.platform.exceptions;

import com.user.ledger.platform.enums.ErrorCodes;

public class UserLedgerPlatformException extends RuntimeException {
    private final int status;
    private final String message;
    private String reason;
    private String errorCode;

    public UserLedgerPlatformException(int status, String message, String reason) {
        super(message);
        this.status = status;
        this.message = message;
        this.reason = reason!=null? reason:"";
    }

    public UserLedgerPlatformException(int status, ErrorCodes errorCode) {
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
