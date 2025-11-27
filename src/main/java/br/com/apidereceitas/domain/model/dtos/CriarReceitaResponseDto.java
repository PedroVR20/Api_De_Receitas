package br.com.apidereceitas.domain.model.dtos;

import java.util.UUID;

public record CriarReceitaResponseDto(
	UUID id,
	String nome,
	String descricao,
	String ingredientes
		) {

}
