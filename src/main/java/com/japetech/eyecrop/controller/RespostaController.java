package com.japetech.eyecrop.controller;

import com.japetech.eyecrop.dtos.RespostaDto;
import com.japetech.eyecrop.exceptions.ErrorResponse;
import com.japetech.eyecrop.models.RespostaModel;
import com.japetech.eyecrop.services.RespostaService;
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

@Tag(name = "Respostas", description = "API para o gerenciamento de respostas no sistema")
@RestController
@RequestMapping("/respostas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RespostaController {

    final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @Operation(summary = "Lista todos as respostas", description = "Lista todos as respostas no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respostas encontradas com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RespostaModel.class))
                    )}),
            @ApiResponse(responseCode = "204", description = "Nenhuma Resposta encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        List<RespostaModel> respostas = respostaService.getAll();
        if (respostas.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(respostas);
        }
    }

    @Operation(summary = "Recupera uma resposta por ID", description = "Recupera os dados de uma resposta a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta encontrada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RespostaModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Resposta não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<RespostaModel> getByid(@PathVariable Long id){
        try {
            RespostaModel res = respostaService.findById(id);
            return ResponseEntity.ok(res);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Salva uma resposta", description = "Salva uma resposta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resposta salva com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RespostaModel.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody RespostaDto respostaDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(respostaService.adicionarResposta(respostaDto));
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar a resposta: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar a resposta: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Exclui uma resposta pelo ID", description = "Exclui uma resposta a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Resposta excluida com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RespostaModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Resposta não encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            respostaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Violação de restrição de integridade: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @Operation(summary = "Atualiza parcialmente uma resposta", description = "Atualiza parcialmente uma resposta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta atualizada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RespostaModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Resposta não encontrado",
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
    public ResponseEntity<Object> partialUpdate(@PathVariable Long id, @RequestBody RespostaDto respostaDto){
        try {
            RespostaModel updateResposta = respostaService.patchResposta(respostaDto, id);
            return ResponseEntity.ok(updateResposta);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao atualizar parcialmente a resposta: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao atualizar parcialmente a resposta: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
