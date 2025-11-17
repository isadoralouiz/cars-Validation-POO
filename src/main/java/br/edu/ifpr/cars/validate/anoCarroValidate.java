package br.edu.ifpr.cars.validate;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class anoCarroValidate implements ConstraintValidator<AnoCarro, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value <= LocalDate.now().getYear();
    }

}