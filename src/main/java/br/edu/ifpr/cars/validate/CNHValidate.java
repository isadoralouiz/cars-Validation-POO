package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNHValidate implements ConstraintValidator<CNH, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("\\d{11}");
    }

}