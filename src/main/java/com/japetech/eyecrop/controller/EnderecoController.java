package com.japetech.eyecrop.controller;

import com.japetech.eyecrop.dtos.EnderecoDto;
import com.japetech.eyecrop.exceptions.ErrorResponse;
import com.japetech.eyecrop.models.EnderecoModel;
import com.japetech.eyecrop.models.UsuarioModel;
import com.japetech.eyecrop.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Endereco", description = "API para o gerenciamento de enderecos no sistema")
@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnderecoController {

    final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Lista todos os endereços", description = "Lista todos os endereços do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EnderecoModel.class))
                    )}),
            @ApiResponse(responseCode = "204", description = "Nenhum Endereço encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get() {
        List<EnderecoModel> enderecos = enderecoService.getAll();
        if(enderecos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(enderecos);
        }
    }

    @Operation(summary = "Recupera um endereço por ID", description = "Recupera os dados de um endereço a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EnderecoModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> getByid(@PathVariable Long id){
        try {
            EnderecoModel endereco = enderecoService.findById(id);
            return ResponseEntity.ok(endereco);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Recupera um endereço pelo estado", description = "Recupera os dados de um endereço a partir do seu estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EnderecoModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EnderecoModel>> getByEstado(@PathVariable String estado){
        List<EnderecoModel> enderecos = enderecoService.findByestado(estado);
        if (enderecos.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(enderecos);
        }
    }


    @Operation(summary = "Recupera um endereço pela cidade", description = "Recupera os dados de um endereço a partir do sua cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EnderecoModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<EnderecoModel>> getByCidade(@PathVariable String cidade){
        List<EnderecoModel> enderecos = enderecoService.findBycidade(cidade);
        if (enderecos.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(enderecos);
        }
    }

    @Operation(summary = "Salva um endereço", description = "Salva um endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço salvo com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoModel.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody EnderecoDto enderecoDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.adicionarEndereco(enderecoDto));
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar o endereço: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar o endereço: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @Operation(summary = "Exclui um Endereço pelo ID", description = "Exclui um Endereço a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço excluido com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            enderecoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualiza um endereço", description = "Atualiza um endereço existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody EnderecoDto enderecoDto){
        try {
            EnderecoModel updateEndereco = enderecoService.putEndereco(enderecoDto, id);
            return ResponseEntity.ok(updateEndereco);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar o endereço: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar o endereço: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Atualiza parcialmente um endereço", description = "Atualiza parcialmente um endereço existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnderecoModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Object> partialUpdate(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto){
        try {
            EnderecoModel updateEndereco = enderecoService.patchEndereco(enderecoDto, id);
            return ResponseEntity.ok(updateEndereco);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar o endereço: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar o endereço: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
