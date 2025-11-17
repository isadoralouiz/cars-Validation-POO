package br.edu.ifpr.cars.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = comentarioValidate.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Comentario {

    String message() default "O comentário contém palavras ofensivas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
