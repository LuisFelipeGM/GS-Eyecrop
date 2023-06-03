package com.japetech.eyecrop.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FotoDto {

    @Schema(example = "https://forbes.com.br/wp-content/uploads/2023/05/carreira-bill-gates-microsoft-conselhos-getty-768x512.jpg")
    @NotBlank(message = "O Link da foto é obrigatório")
    private String foto;

    @Schema(example = "1")
    @NotNull(message = "O Id do Usuario é obrigatório")
    private Long idUsuario;

}
