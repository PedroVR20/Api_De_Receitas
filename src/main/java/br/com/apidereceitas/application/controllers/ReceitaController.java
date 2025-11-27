package br.com.apidereceitas.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apidereceitas.domain.model.dtos.CriarReceitaRequestDto;
import br.com.apidereceitas.domain.model.dtos.CriarReceitaResponseDto;
import br.com.apidereceitas.domain.model.entities.Receita;
import br.com.apidereceitas.domain.services.ReceitaService;
@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired 
    private ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<CriarReceitaResponseDto> criarReceita(
            @RequestBody CriarReceitaRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(receitaService.criarReceita(dto));
    }

    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas() {
        return ResponseEntity.ok(receitaService.listarReceitas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable UUID id) {
        receitaService.deletarReceita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{nome}") 
    public ResponseEntity<List<Receita>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(receitaService.buscarPorNome(nome));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(
            @PathVariable UUID id,
            @RequestBody CriarReceitaRequestDto dto) {

        return ResponseEntity.ok(receitaService.atualizarReceita(id, dto));
    }
}
