package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Driver {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ]+$", message = "O nome não pode conter espaços ou caracteres inválidos.")
    String name;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve estar em um formato válido.")
    @Pattern(regexp = "^[^\\s]+$", message = "O e-mail não pode conter espaços em branco.")
    String email;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(
        regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2})$",
        message = "O CPF deve estar no formato 000.000.000-00 ou apenas números."
    )
    String cpf;

    LocalDate birthDate;
    
    
}
