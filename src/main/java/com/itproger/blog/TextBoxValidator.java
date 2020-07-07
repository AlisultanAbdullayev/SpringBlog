package com.itproger.blog;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.validation.Validator;

public abstract class TextBoxValidator implements Validator {
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(
                errors, "title", "required.title");
    }
}
