package com.roboticsinc.robotinventory.exception;

import java.io.Serializable;

public class ServiceError implements Serializable {

    private static final long serialVersionUID = -4645994987672908971L;

    private final int errorCode;
    private final Object error;

    public ServiceError(int errorCode, Object error) {
        this.errorCode = errorCode;
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Object getError() {
        return error;
    }
}