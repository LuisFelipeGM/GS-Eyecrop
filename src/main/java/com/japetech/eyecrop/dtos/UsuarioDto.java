package com.japetech.eyecrop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioDto {

    @Schema(example = "Luís Felipe Garcia Menezes")
    private String nome;

    @Schema(example = "luis.felipe@gmail.com")
    private String email;

    @Schema(example = "Luis123456#")
    private String senha;

}
