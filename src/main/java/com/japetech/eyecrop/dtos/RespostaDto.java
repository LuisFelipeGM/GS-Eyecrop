package com.japetech.eyecrop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RespostaDto {

    @Schema(example = "O produto tem 86% de qualidade")
    @NotBlank(message = "A resposta da foto é obrigatório")
    private String resposta;

    @Schema(example = "1")
    @NotNull(message = "O Id da Foto é obrigatório")
    private Long idFoto;

}
