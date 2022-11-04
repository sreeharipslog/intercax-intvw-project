package com.roboticsinc.robotinventory.exception;

import java.io.Serializable;

public class ServiceError implements Serializable {

    private static final long serialVersionUID = -4645994987672908971L;

    private final int status;
    private final Object data;

    public ServiceError(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}