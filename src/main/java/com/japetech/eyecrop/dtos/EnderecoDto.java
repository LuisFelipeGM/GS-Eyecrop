package com.japetech.eyecrop.dtos;

import com.japetech.eyecrop.models.UsuarioModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDto {

    @Schema(example = "Rua Fidêncio Ramos")
    @NotBlank(message = "O Logradouro é obrigatório")
    private String logradouro;

    @Schema(example = "1")
    @NotNull(message = "O Numero é obrigatório")
    private Integer numero;

    @Schema(example = "São Paulo")
    @NotBlank(message = "A Cidade é obrigatório")
    private String cidade;

    @Schema(example = "São Paulo")
    @NotBlank(message = "O Estado é obrigatório")
    private String estado;

    @Schema(example = "1")
    @NotNull(message = "O Id do Usuario é obrigatório")
    private Long idUsuario;
}
