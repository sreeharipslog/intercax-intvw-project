package com.roboticsinc.robotinventory.util;

import com.roboticsinc.robotinventory.constant.ErrorConstants.BusinessError;
import com.roboticsinc.robotinventory.exception.BusinessException;

import java.time.DateTimeException;
import java.time.Year;

/**
 * Utility methods for Robot Inventory
 *
 * @author sreeharipslog
 */
public interface InventoryUtils {

    /**
     * Convert String to Year
     *
     * @param yearString year string
     * @return year string
     * @throws BusinessException iff invalid yearString
     */
    static String validYear(String yearString) {
        try {
            Year.parse(yearString);
            return yearString;
        } catch (DateTimeException e) {
            throw new BusinessException(BusinessError.YEAR_INVALID.getErrorCode(),
                    BusinessError.YEAR_INVALID.getMessage());
        }
    }
}