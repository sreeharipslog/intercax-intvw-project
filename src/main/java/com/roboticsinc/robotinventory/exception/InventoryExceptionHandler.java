package com.roboticsinc.robotinventory.exception;

import com.roboticsinc.robotinventory.constant.ErrorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Global Exception handler for Robot Inventory Service. This helps to restrict unwanted info from reaching clients
 *
 * @author sreeharipslog
 */

@ControllerAdvice
public class InventoryExceptionHandler {

    /**
     * Can extend {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler } and
     * override its method for some of the below exception.
     */

    private static final Logger logger = LoggerFactory.getLogger(InventoryExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({BusinessException.class, MethodArgumentNotValidException.class, Exception.class})
    public ResponseEntity<ServiceError> handleException(Exception exception) {
        logger.error("Inventory Service Exception");
        if (exception instanceof BusinessException) return handleBusinessException((BusinessException) exception);
        else if (exception instanceof MethodArgumentNotValidException)
            return handleValidationException((MethodArgumentNotValidException) exception);
        else return handleGenericException(exception);
    }

    private ResponseEntity<ServiceError> handleGenericException(Exception exception) {
        logger.error("Internal Exception", exception);
        return ResponseEntity.ok().body(new ServiceError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                resolveErrorMessage(ErrorConstants.ErrorMessages.INTERNAL_SERVER_ERROR)));
    }

    private ResponseEntity<ServiceError> handleValidationException(MethodArgumentNotValidException exception) {
        logger.error("Validation Exception", exception);
        List<String> violations = exception.getFieldErrors().stream()
                .map(x -> x.getField() + " : " + x.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.ok().body(new ServiceError(HttpStatus.BAD_REQUEST.value(), violations));
    }

    private ResponseEntity<ServiceError> handleBusinessException(BusinessException exception) {
        logger.error("Business Exception", exception);
        return ResponseEntity.ok()
                .body(new ServiceError(exception.getErrorCode(), resolveErrorMessage(exception.getMessage())));
    }

    private String resolveErrorMessage(String errorMessage) {
        return messageSource.getMessage(errorMessage, null, ErrorConstants.CONFIG_ERROR, Locale.US);
    }
}