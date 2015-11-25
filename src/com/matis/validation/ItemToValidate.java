package com.matis.validation;

/**
 *
 * @author MiguelAngel
 */
public class ItemToValidate {
    private String type;
    private String attrName;
    private boolean required;
    private Object value;    
    
    private ValidationErrorCode errorType;

    public ItemToValidate(String type, String attrName, boolean required, Object value) {
        this.type = type;
        this.attrName = attrName;
        this.required = required;
        this.value = value;
    }
    
    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    
    
}
