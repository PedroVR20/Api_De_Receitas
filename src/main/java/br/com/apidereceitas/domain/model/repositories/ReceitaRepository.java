package br.com.apidereceitas.domain.model.repositories;



import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apidereceitas.domain.model.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, UUID>{ 

	// método alternativo para buscar receita por nome
	List<Receita> findByNomeContainingIgnoreCase(String nome);
	
}
