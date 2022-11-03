package com.roboticsinc.robotinventory.exception;

/**
 * Business exception for Robot Inventory.<br>
 * <b>Note</b>: From Spring 5, if needed we can make use of
 * {@link org.springframework.web.server.ResponseStatusException} instead of creating multiple
 * custom classes. One type with multiple error codes
 *
 * @author sreeharipslog
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 6596541092893697014L;

    private final int errorCode;
    private final String message;

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}