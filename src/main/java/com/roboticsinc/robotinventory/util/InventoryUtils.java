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
     * @return {@link Year}
     * @throws BusinessException iff invalid yearString
     */
    static Year stringToYear(String yearString) {
        try {
            return Year.parse(yearString);
        } catch (DateTimeException e) {
            throw new BusinessException(BusinessError.YEAR_INVALID.getErrorCode(),
                    BusinessError.YEAR_INVALID.getMessage());
        }
    }
}