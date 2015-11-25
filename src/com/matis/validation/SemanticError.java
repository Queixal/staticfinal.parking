package com.matis.validation;

/**
 *
 * @author MiguelAngel
 */
public class SemanticError {
    private String attrName;
    private ValidationErrorCode errorCode;

    public SemanticError(String attrName, ValidationErrorCode errorCode) {
        this.attrName = attrName;
        this.errorCode = errorCode;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public ValidationErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ValidationErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
}
