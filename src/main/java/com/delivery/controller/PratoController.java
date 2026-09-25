package com.delivery.controller;

import com.delivery.dto.PratoRequestDTO;
import com.delivery.dto.PratoResponseDTO;
import com.delivery.service.PratoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;

    // Injeção na dependência do Service no via  construtor
    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    // GET /pratos ou GET /pratos?categoria=vegano
    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listar(
            @RequestParam(required = false) String categoria) {

        if (categoria != null && !categoria.isBlank()) {
            return ResponseEntity.ok(pratoService.listarPorCategoria(categoria));
        }
        return ResponseEntity.ok(pratoService.listarTodos());
    }

    // GET /pratos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            PratoResponseDTO prato = pratoService.buscarPorId(id);
            return ResponseEntity.ok(prato);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /pratos
    @PostMapping
    public ResponseEntity<PratoResponseDTO> criar(@Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO pratoCriado = pratoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pratoCriado);
    }

    // PUT /pratos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PratoRequestDTO dto) {
        try {
            PratoResponseDTO pratoAtualizado = pratoService.atualizar(id, dto);
            return ResponseEntity.ok(pratoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /pratos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            pratoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}