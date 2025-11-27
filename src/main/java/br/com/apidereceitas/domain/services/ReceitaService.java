package br.com.apidereceitas.domain.services;



import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.apidereceitas.domain.model.dtos.CriarReceitaRequestDto;
import br.com.apidereceitas.domain.model.dtos.CriarReceitaResponseDto;
import br.com.apidereceitas.domain.model.entities.Receita;
import br.com.apidereceitas.domain.model.repositories.ReceitaRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public CriarReceitaResponseDto criarReceita(CriarReceitaRequestDto dto) {

        Receita receita = new Receita();
        receita.setNome(dto.nome());
        receita.setDescricao(dto.descricao());
        receita.setIngredientes(dto.ingredientes());

        Receita receitaSalva = receitaRepository.save(receita);

        return new CriarReceitaResponseDto(
                receitaSalva.getId(),
                receitaSalva.getNome(),
                receitaSalva.getDescricao(),
                receitaSalva.getIngredientes()
        );
    }

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public void deletarReceita(UUID id) {
        Receita receita = receitaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        receitaRepository.delete(receita);
    }

    public Receita atualizarReceita(UUID id, CriarReceitaRequestDto dto) {

        Receita receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        if (dto.nome() != null) receitaExistente.setNome(dto.nome());
        if (dto.descricao() != null) receitaExistente.setDescricao(dto.descricao());
        if (dto.ingredientes() != null) receitaExistente.setIngredientes(dto.ingredientes());

        return receitaRepository.save(receitaExistente);
    }

    public List<Receita> buscarPorNome(String nome) {
        return receitaRepository.findByNomeContainingIgnoreCase(nome);
    }
}

