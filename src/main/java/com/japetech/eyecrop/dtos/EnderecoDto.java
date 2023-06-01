package com.japetech.eyecrop.dtos;

import com.japetech.eyecrop.models.UsuarioModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDto {

    @Schema(example = "Rua Fidêncio Ramos")
    private String logradouro;

    @Schema(example = "1")
    private Integer numero;

    @Schema(example = "São Paulo")
    private String cidade;

    @Schema(example = "São Paulo")
    private String estado;

    @Schema(example = "1")
    private Long idUsuario;
}
