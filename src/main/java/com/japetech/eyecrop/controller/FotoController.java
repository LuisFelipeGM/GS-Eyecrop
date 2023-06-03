package com.japetech.eyecrop.controller;

import com.japetech.eyecrop.dtos.FotoDto;
import com.japetech.eyecrop.exceptions.ErrorResponse;
import com.japetech.eyecrop.models.FotoModel;
import com.japetech.eyecrop.services.FotoService;
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

@Tag(name = "Foto", description = "API para o gerenciamento de fotos no sistema")
@RestController
@RequestMapping("/fotos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FotoController {

    final FotoService fotoService;

    public FotoController(FotoService fotoService) {
        this.fotoService = fotoService;
    }

    @Operation(summary = "Lista todos as fotos", description = "Lista todos as fotos no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fotos encontradas com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FotoModel.class))
                    )}),
            @ApiResponse(responseCode = "204", description = "Nenhuma Foto encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/")
    public ResponseEntity<Object> get(){
        List<FotoModel> fotos = fotoService.getAll();
        if (fotos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(fotos);
        }
    }

    @Operation(summary = "Recupera uma foto por ID", description = "Recupera os dados de uma foto a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto encontrada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FotoModel.class))
                    )}),
            @ApiResponse(responseCode = "404", description = "Foto não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<FotoModel> getByid(@PathVariable Long id) {
        try {
            FotoModel foto = fotoService.findById(id);
            return ResponseEntity.ok(foto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Salva uma foto", description = "Salva uma foto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "foto salva com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FotoModel.class)
                    )}),
            @ApiResponse(responseCode = "409", description = "Violação de restrição de dados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "Conflict")
                    )})
    })
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody FotoDto fotoDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fotoService.adicionarFoto(fotoDto));
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar a foto: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao salvar a foto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Exclui uma foto pelo ID", description = "Exclui uma foto a partir do seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Foto excluida com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FotoModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "No content")
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            fotoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Violação de restrição de integridade: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @Operation(summary = "Atualiza parcialmente uma foto", description = "Atualiza parcialmente uma foto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto atualizada com sucesso",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FotoModel.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Foto não encontrado",
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
    public ResponseEntity<Object> partialUpdate(@PathVariable Long id, @RequestBody FotoDto fotoDto){
        try {
            FotoModel updateFoto = fotoService.patchFoto(fotoDto, id);
            return ResponseEntity.ok(updateFoto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao atualizar parcialmente a foto: " + e.getRootCause().getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro ao atualizar parcialmente a foto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
