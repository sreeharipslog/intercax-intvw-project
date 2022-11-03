package com.roboticsinc.robotinventory.exception;

/**
 * Business exception for Robot Inventory
 *
 * @author sreeharipslog
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 6596541092893697014L;

    private final int errorCode;

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}