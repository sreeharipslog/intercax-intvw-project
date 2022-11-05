package com.roboticsinc.robotinventory.exception;

import com.roboticsinc.robotinventory.constant.ErrorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global Exception handler for Robot Inventory Service. This helps to restrict unwanted info from reaching clients
 *
 * @author sreeharipslog
 */

@ControllerAdvice
public class InventoryExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(InventoryExceptionHandler.class);

    @ExceptionHandler({BusinessException.class, ConstraintViolationException.class, RuntimeException.class})
    public ResponseEntity<ServiceError> handleException(Exception exception) {
        logger.error("Service Exception :: ", exception);
        if (exception instanceof BusinessException) return handleBusinessException((BusinessException) exception);
        else if (exception instanceof ValidationException)
            return handleViolationException((ConstraintViolationException) exception);
        else return handleGenericException();
    }

    private ResponseEntity<ServiceError> handleGenericException() {
        return ResponseEntity.ok()
                .body(new ServiceError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorConstants.ErrorMessages.INTERNAL_SERVER_ERROR));
    }

    private ResponseEntity<ServiceError> handleViolationException(ConstraintViolationException exception) {
        List<String> violations = exception.getConstraintViolations().stream()
                .map(x -> x.getPropertyPath().toString() + ":" + x.getMessage()).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ServiceError(HttpStatus.BAD_REQUEST.value(), violations));
    }

    private ResponseEntity<ServiceError> handleBusinessException(BusinessException exception) {
        return ResponseEntity.ok().body(new ServiceError(exception.getErrorCode(), exception.getMessage()));
    }
}