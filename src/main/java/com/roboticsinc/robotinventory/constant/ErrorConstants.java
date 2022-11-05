package com.roboticsinc.robotinventory.constant;

/**
 * Error Constants
 */
public interface ErrorConstants {


    /**
     * Internationalized error messages
     */
    interface ErrorMessages {
        String INVALID_ROBOT_STATE = "invalid.robot.state";
        String ROBOT_NOT_FOUND = "robot.not.found";
        String INVALID_REQUEST = "invalid.request";
        String INTERNAL_SERVER_ERROR = "internal.server.error";

        String INVALID_YEAR = "invalid.year";
    }

    /**
     * Business error enums with error code and error messages
     */
    enum BusinessError {
        INVENTORY_EMPTY(1000, "Robot inventory is empty"), ROBOT_NOT_FOUND(1001,
                ErrorMessages.ROBOT_NOT_FOUND), INVALID_ROBOT_STATE(1002,
                ErrorMessages.INVALID_ROBOT_STATE), YEAR_INVALID(1003, ErrorMessages.INVALID_YEAR);

        private final int errorCode;
        private final String message;

        BusinessError(int errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }
    }
}