package com.subutai.nova.web.ws.arduino;

public class FailureResponse {

    public static final int ERROR_TIMEOUT = 0;
    public static final int ERROR_IOEXCEPTION = 1;
    public static final int ERROR_REGISTRY = 2;

    public static final FailureResponse Timeout = new FailureResponse(ERROR_TIMEOUT);
    public static final FailureResponse IOException = new FailureResponse(ERROR_IOEXCEPTION);
    public static final FailureResponse RegistryError = new FailureResponse(ERROR_REGISTRY);

    private String message;
    private int errorCode;

    public FailureResponse(int errorCode) {
        this.errorCode = errorCode;
        this.message = "";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
