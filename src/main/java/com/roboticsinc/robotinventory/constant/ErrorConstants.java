package com.roboticsinc.robotinventory.constant;

public interface ErrorConstants {

    String INTERNAL_SERVER_ERROR = "Error occurred while processing your request. Please contact technical support.";
    String INVALID_REQUEST = "Request is invalid. Check API specification.";

    enum BusinessError {
        INVENTORY_EMPTY (1000, "Robot inventory is empty"),
        ROBOT_NOT_FOUND(1001, "Robot not found in Inventory"), INVALID_ROBOT_STATE(1002, "Invalid Robot State");

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