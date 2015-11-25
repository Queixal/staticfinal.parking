package com.matis.validation;

import com.matis.parking.server.Messages;

/**
 *
 * @author MiguelAngel
 */
public enum ValidationErrorCode {
    WRONG_DATE_FOMAT(0, Messages.WRONG_DATE_FORMAT),
    UNVALIDABLE(1, Messages.UNVALIDABLE_VALUE),
    NOT_INT(2, Messages.IS_NOT_INT),
    NOT_DOUBLE(3, Messages.IS_NOT_DOUBLE),
    IS_BLANK(4, Messages.IS_BLANK_VALUE),
    IS_NULL(5, Messages.IS_NULL_VALUE),
    NOT_A_DAY(6, Messages.NOT_A_DAY),
    TWO_EQUIVALENT_DAYS(7, Messages.TWO_EQUIVALENT_DAYS),
    TOO_MUCH_DAYS(8, Messages.TOO_MUCH_DAYS);
    
    private int id;
    private String message;

    private ValidationErrorCode(int id, String message) {
        this.id = id;
        this.message = message;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
