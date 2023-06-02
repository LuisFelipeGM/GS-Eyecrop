package com.japetech.eyecrop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDto {

    @Schema(example = "Luís Felipe Garcia Menezes")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Schema(example = "luis.felipe@gmail.com")
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Schema(example = "Luis123456#")
    @NotBlank(message = "A senha é obrigatório")
    private String senha;

}
