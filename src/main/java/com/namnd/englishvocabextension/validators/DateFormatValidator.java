package com.namnd.englishvocabextension.validators;

import com.namnd.englishvocabextension.utils.Util;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author itsol.hungtt on 12/25/2020
 * Copyright @DONG.NV
 */
public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, String> {

    @Override
    public void initialize(DateFormatConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext ct) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return Util.validateStandardDateFormat(s);
    }

}
