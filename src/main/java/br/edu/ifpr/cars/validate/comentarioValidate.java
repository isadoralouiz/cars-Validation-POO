package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class comentarioValidate implements ConstraintValidator< Comentario, String> {

    private final List<String> palavrasProibidas = Arrays.asList(
        "burro", "idiota", "lixo", "otario", "besta, matar", "estupido", "imbecil", "cretino", "vagabundo", "canalha"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true; 
        }

        String texto = value.toLowerCase();

        for (String proibida : palavrasProibidas) {
            if (texto.contains(proibida)) {
                return false;
            }
        }

        return true;
    }
}
