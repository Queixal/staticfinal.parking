package com.matis.validation;

import com.matis.parking.entities.Day;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import com.matis.utils.DateUtils;
import com.matis.utils.DoubleUtils;
import com.matis.utils.IntegerUtils;
import com.matis.utils.StringUtils;

/**
 *
 * @author MiguelAngel
 */
public class Validator {

    public List<SemanticError> validate(List<ItemToValidate> items) {
        List<SemanticError> errors = new ArrayList<SemanticError>();
        for (ItemToValidate item : items) {
            SemanticError errorReached = null;
            if (item.getType().equalsIgnoreCase(String.class.getSimpleName())) {
                if (!StringUtils.isNull((String) item.getValue())) {
                    if (StringUtils.isBlank((String) item.getValue())) {
                        errorReached = new SemanticError(item.getAttrName(),
                                ValidationErrorCode.IS_BLANK);
                    }
                } else if (item.isRequired()) {
                    errorReached = new SemanticError(item.getAttrName(),
                            ValidationErrorCode.IS_NULL);
                }
            } else if (item.getType().equalsIgnoreCase(Date.class.getSimpleName())) {
                if (!StringUtils.isNull((String) item.getValue())) {
                    if (StringUtils.isBlank((String) item.getValue())) {
                        errorReached = new SemanticError(item.getAttrName(),
                                ValidationErrorCode.IS_BLANK);
                    } else if (!DateUtils.isValidFormat((String) item.getValue(), "HH:mm")) {
                        errorReached = new SemanticError(item.getAttrName(),
                                ValidationErrorCode.WRONG_DATE_FOMAT);
                    }
                } else if (item.isRequired()) {
                    errorReached = new SemanticError(item.getAttrName(),
                            ValidationErrorCode.IS_NULL);
                }
            } else if (item.getType().equalsIgnoreCase(Double.class.getSimpleName())) {
                if (!DoubleUtils.isNull((String) item.getValue())) {
                    if (!DoubleUtils.isDouble((String) item.getValue())) {
                        errorReached = new SemanticError(item.getAttrName(),
                                ValidationErrorCode.NOT_DOUBLE);
                    }
                } else if (item.isRequired()) {
                    errorReached = new SemanticError(item.getAttrName(),
                            ValidationErrorCode.IS_NULL);
                }

            } else if (item.getType().equalsIgnoreCase(Integer.class.getSimpleName())) {
                if (!IntegerUtils.isNull((String) item.getValue())) {
                    if (!IntegerUtils.isInteger((String) item.getValue())) {
                        errorReached = new SemanticError(item.getAttrName(),
                                ValidationErrorCode.NOT_INT);
                    }
                } else if (item.isRequired()) {
                    errorReached = new SemanticError(item.getAttrName(),
                            ValidationErrorCode.IS_NULL);
                }

            } else if (item.getType().equalsIgnoreCase(Day.class.getSimpleName())) {
                if (!StringUtils.isNull((String) item.getValue())) {
                    StringTokenizer tokenized = new StringTokenizer((String) item.getValue(), ";");
                    if (tokenized.countTokens() > 7) {
                        errorReached = new SemanticError(item.getAttrName(),
                                ValidationErrorCode.TOO_MUCH_DAYS);
                    }
                    String[] days = new String[tokenized.countTokens()];
                    int count = 0;
                    boolean errorFound = false;
                    while (tokenized.hasMoreElements() && !errorFound) {
                        String token = tokenized.nextToken();
                        if (!IntegerUtils.isInteger(token)) {
                            errorFound = true;
                            errorReached = new SemanticError(item.getAttrName(),
                                    ValidationErrorCode.NOT_A_DAY);
                        } else if (!Day.exist(Integer.parseInt(token))) {

                            errorFound = true;
                            errorReached = new SemanticError(item.getAttrName(),
                                    ValidationErrorCode.NOT_A_DAY);
                        } else {
                            for (int i = 0; i < count && !errorFound; i++) {
                                if (days[i].equalsIgnoreCase(token)) {
                                    errorFound = true;
                                    errorReached = new SemanticError(item.getAttrName(),
                                            ValidationErrorCode.TWO_EQUIVALENT_DAYS);
                                }
                            }
                        }
                    }
                } else if (item.isRequired()) {
                    errorReached = new SemanticError(item.getAttrName(),
                            ValidationErrorCode.IS_NULL);
                }

            } else {
                errorReached = new SemanticError(item.getAttrName(),
                        ValidationErrorCode.UNVALIDABLE);
            }

            if (errorReached != null) {
                errors.add(errorReached);
            }
        }
        return errors;
    }

}
